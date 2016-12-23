package com.ruoxu.eventbus;

/**
 * Created by wangli on 16/12/23.
 */

/**
 * <p>
 * 该类是描述一个函数唯一性的，参数类型、tag两个条件
 * Subscription}, 并且在接到消息时调用所有订阅者对应的函数.
 */


public class EventType {

    EventType(String tag){
        this.tag = tag;
    }
    /**
     * 默认的tag
     */
    public static final String DEFAULT_TAG = "default_tag";

    public String tag = DEFAULT_TAG;
    public Object event ;
}
