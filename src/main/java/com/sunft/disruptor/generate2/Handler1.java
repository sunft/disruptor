package com.sunft.disruptor.generate2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by sunft on 2018/7/3.
 * 数据消费者1
 */
public class Handler1 implements EventHandler<Trade>,WorkHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(trade);
    }

    @Override
    public void onEvent(Trade trade) throws Exception {
        System.out.println("handler1: set name");
        trade.setName("h1");
        Thread.sleep(1000);
    }

}
