package com.sunft.disruptor.generate2;

import com.lmax.disruptor.EventHandler;

/**
 * Created by sunft on 2018/7/3.
 * 处理器3
 */
public class Handler3 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long sequence,  boolean endOfBatch) throws Exception {
        System.out.println("handler3:name: " + trade.getName() + " , price: " + trade.getPrice()
                + ";  instance: " + trade.toString());
    }

}
