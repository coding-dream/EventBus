package com.ruoxu.eventbus;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by wangli on 16/12/23.
 */
public class EventDispatcher {

    private Map<EventType, CopyOnWriteArrayList<Subscriber>> mSubcriberMap;

    public EventDispatcher(Map<EventType, CopyOnWriteArrayList<Subscriber>> mSubcriberMap) {
        this.mSubcriberMap = mSubcriberMap;
    }


//    /**
//     * 将接收方法执行在UI线程
//     */
//    EventHandler mUIThreadEventHandler = new UIThreadEventHandler();
//
//    /**
//     * 哪个线程执行的post,接收方法就执行在哪个线程
//     */
//    EventHandler mPostThreadHandler = new DefaultEventHandler();
//
//    /**
//     * 异步线程中执行订阅方法
//     */
//    EventHandler mAsyncEventHandler = new AsyncEventHandler();

    public void dispatchEvents(ThreadLocal<Queue<EventType>> threadLocal,Object aEvent) {
        Queue<EventType> eventsQueue = threadLocal.get();
        while (eventsQueue.size() > 0) {
            deliveryEvent(eventsQueue.poll(), aEvent);
        }
    }

    private void deliveryEvent(EventType type, Object aEvent) {
        // 如果有缓存则直接从缓存中取
        List<EventType> eventTypes = getMatchedEventTypes(type, aEvent);
        // 迭代所有匹配的事件并且分发给订阅者
        for (EventType eventType : eventTypes) {
            handleEvent(eventType, aEvent);
        }

    }


    private void handleEvent(EventType eventType, Object aEvent) {
        List<Subscriber> subscriptions = mSubcriberMap.get(eventType);

        for (Subscriber subscriber : subscriptions) {
            subscriber.receive(aEvent);
        }
    }




    private List<EventType> getMatchedEventTypes(EventType type, Object aEvent) {

        return null;
    }

}
