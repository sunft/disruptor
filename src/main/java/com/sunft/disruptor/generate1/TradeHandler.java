package com.sunft.disruptor.generate1;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * Created by sunft on 2018/7/2.
 * 消费者,数据处理器,可以实现两个接口中的任何一个,或者两个都实现
 * 这里实现两个接口是为了演示两种消息处理器的使用
 */
public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long sequence, boolean endOfBatch) throws Exception {
        //调用下一个方法
        this.onEvent(trade);
    }

    @Override
    public void onEvent(Trade trade) throws Exception {
        //这里做具体的消费逻辑
        trade.setId(UUID.randomUUID().toString());//简单生成下ID
        System.out.println(trade.getId());
    }
}
