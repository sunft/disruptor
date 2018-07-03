package com.sunft.disruptor.generate2;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by sunft on 2018/7/3.
 * 服务发布者
 */
public class TradePublisher implements Runnable {

    private Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    private static final int LOOP = 1;//模拟百万次交易的发生

    public TradePublisher(CountDownLatch latch,Disruptor<Trade> disruptor) {
        this.disruptor=disruptor;
        this.latch=latch;
    }

    public Disruptor<Trade> getDisruptor() {
        return disruptor;
    }

    public void setDisruptor(Disruptor<Trade> disruptor) {
        this.disruptor = disruptor;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeEventTranslator tradeTransloator = new TradeEventTranslator();
        for(int i=0;i<LOOP;i++){
            disruptor.publishEvent(tradeTransloator);
        }
        //完成后发出通知
        latch.countDown();
    }
}

class TradeEventTranslator implements EventTranslator<Trade> {

    private Random random = new Random();

    @Override
    public void translateTo(Trade event, long sequence) {
        this.generateTrade(event);
    }

    /**
     * 相当于生产端填充数据
     * @param trade 原始为空,填充后返回即可
     * @return 生产者生产出来的数据
     */
    private Trade generateTrade(Trade trade) {
        trade.setPrice(random.nextDouble() * 9999);
        return  trade;
    }

}


