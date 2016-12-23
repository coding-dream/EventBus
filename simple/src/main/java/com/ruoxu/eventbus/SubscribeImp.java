package com.ruoxu.eventbus;

/**
 * Created by wangli on 16/12/23.
 */
public class SubscribeImp implements Subscriber {
    private String name;

    public SubscribeImp(String name){
        this.name  = name;
    }

    @Override
    public void receive(Object object) {
        System.out.println(name + ": 收到消息了"+object);

    }
}
