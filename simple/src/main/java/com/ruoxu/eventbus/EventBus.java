package com.ruoxu.eventbus;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wangli on 16/12/23.
 */
public class EventBus {

    private static EventBus sDefaultBus;

    //Map存储 EventType 为key，CopyOnWriteArrayList为 订阅者Subscriber集合
    private final Map<EventType,CopyOnWriteArrayList<Subscriber>> mSubcriberMap = new ConcurrentHashMap<>();


    EventDispatcher mDispatcher = new EventDispatcher(mSubcriberMap);

    ThreadLocal<Queue<EventType>> mLocalEvents = new ThreadLocal<Queue<EventType>>() {
        protected Queue<EventType> initialValue() {
            return new ConcurrentLinkedQueue<EventType>();
        };
    };

    public static EventBus getDefault() {
        if (sDefaultBus == null) {
            synchronized (EventBus.class) {
                if (sDefaultBus == null) {
                    sDefaultBus = new EventBus();
                }
            }
        }
        return sDefaultBus;
    }


    public void notifyDataChange(Object event){ //等价于EventBus的 post发送事件
        notifyDataChange(event, EventType.DEFAULT_TAG);
    }

    public void notifyDataChange(Object event, String tag) {
        if (event == null) {
            throw new RuntimeException("事件为空");
        }
        mLocalEvents.get().offer(new EventType(tag));

        mDispatcher.dispatchEvents(mLocalEvents,event);
    }

    public void register(){
        

    }



}
