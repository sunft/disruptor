package com.sunft.disruptor.generate2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by sunft on 2018/7/3.
 * 处理器5
 */
public class Handler5 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(trade);
    }

    @Override
    public void onEvent(Trade trade) throws Exception {
        System.out.println("handler5: get price : " + trade.getPrice());
        trade.setPrice(trade.getPrice() + 3.0);
    }

}
