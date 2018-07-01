package com.sunft.disruptor.base;

import com.lmax.disruptor.EventFactory;

/**
 * Created by sunft on 2018/7/1.
 * 工厂方法
 */
public class LongEventFactory implements EventFactory<LongEvent> {


    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
