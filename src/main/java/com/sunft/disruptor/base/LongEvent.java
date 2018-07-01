package com.sunft.disruptor.base;

/**
 * Created by sunft on 2018/7/1.
 * 需要生产的数据
 */
public class LongEvent {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
