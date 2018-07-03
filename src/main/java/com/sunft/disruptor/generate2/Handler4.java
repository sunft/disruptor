package com.sunft.disruptor.generate2;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by sunft on 2018/7/3.
 * 处理器4
 */
public class Handler4 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(trade);
    }

    @Override
    public void onEvent(Trade trade) throws Exception {
        System.out.println("handler4: get name :" + trade.getName());
        trade.setName(trade.getName() + "h4");
    }

}
