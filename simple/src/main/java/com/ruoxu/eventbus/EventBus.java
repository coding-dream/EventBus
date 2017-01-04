package com.ruoxu.eventbus;

import android.util.Log;

import java.util.Iterator;
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


    public Subscriber register(Subscriber subscriber){
        register(subscriber,EventType.DEFAULT_TAG);
        return subscriber;
    }

    public Subscriber register(Subscriber subscriber,String tag){
        EventType eventType = new EventType(tag);
        CopyOnWriteArrayList<Subscriber> subscribers = mSubcriberMap.get(eventType);
        if (subscribers == null) {
            subscribers = new CopyOnWriteArrayList<>();
        }

        if (subscribers.contains(subscriber)) {
            Log.d("EventBus", "已经注册过了该观察者了");
        } else {
            subscribers.add(subscriber);
        }

        mSubcriberMap.put(eventType, subscribers);
        return subscriber;
    }

    public void unregister(Subscriber subscriber) {
        if (subscriber == null) {
            return;
        }
        synchronized (this) {
            Iterator<CopyOnWriteArrayList<Subscriber>> iterator = mSubcriberMap.values().iterator();
            while (iterator.hasNext()) {

                CopyOnWriteArrayList<Subscriber> subscriptions = iterator.next();
                if (subscriptions != null) {

                    Iterator<Subscriber> subIterator = subscriptions.iterator();
                    while (subIterator.hasNext()) {
                        Subscriber subscription = subIterator.next();

                        if (subscription.equals(subscriber)) {
                            Log.d("", "### 移除订阅 " + subscriber.getClass().getName());
                            subscriptions.remove(subscription);
                        }
                    }


                }

                // 如果针对某个Event的订阅者数量为空了,那么需要从map中清除
                if (subscriptions == null || subscriptions.size() == 0) {
                    iterator.remove();
                }



            }

        }

    }



}
