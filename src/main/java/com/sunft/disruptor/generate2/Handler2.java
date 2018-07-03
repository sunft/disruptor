package com.sunft.disruptor.generate2;

import com.lmax.disruptor.EventHandler;

/**
 * Created by sunft on 2018/7/3.
 * 处理器2
 */
public class Handler2 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long sequence,  boolean endOfBatch) throws Exception {
        System.out.println("handler2: set price");
        trade.setPrice(17.0);
        Thread.sleep(1000);
    }

}
