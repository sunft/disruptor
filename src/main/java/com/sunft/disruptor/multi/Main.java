package com.sunft.disruptor.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by sunft on 2018/7/4.
 * 测试类
 */
public class Main {

    public static void main(String[] args) throws Exception {

        //创建ringBuffer
        RingBuffer<Order> ringBuffer =
                //指定多个生产者模式
                RingBuffer.create(ProducerType.MULTI,
                        new EventFactory<Order>() {
                            @Override
                            public Order newInstance() {
                                return new Order();
                            }
                        },
                        1024 * 1024,
                        new YieldingWaitStrategy());

        //协调者
        SequenceBarrier barriers = ringBuffer.newBarrier();

        //创建三个消费者
        Consumer[] consumers = new Consumer[3];
        for(int i = 0; i < consumers.length; i++){
            consumers[i] = new Consumer("c" + i);
        }

        WorkerPool<Order> workerPool =
                new WorkerPool<Order>(ringBuffer,
                        barriers,
                        new IntEventExceptionHandler(),
                        consumers);

        //将消费进度传给生产者，让生产者自己决定生产数据的速度
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        //
        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            final Producer p = new Producer(ringBuffer);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //阻塞，放置Producer没有实例化好,就调用数据
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //每个生产者生产100条数据
                    for(int j = 0; j < 100; j ++){
                        p.onData(UUID.randomUUID().toString());
                    }
                }
            }).start();
        }
        //等待2秒,等待生产者对象被完全创建好
        Thread.sleep(2000);
        System.out.println("---------------开始生产-----------------");
        latch.countDown();
        Thread.sleep(5000);
        System.out.println("总数:" + consumers[0].getCount() );
    }

    static class IntEventExceptionHandler implements ExceptionHandler {
        public void handleEventException(Throwable ex, long sequence, Object event) {}
        public void handleOnStartException(Throwable ex) {}
        public void handleOnShutdownException(Throwable ex) {}
    }

}
