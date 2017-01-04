package com.ruoxu.eventbus;

import com.ruoxu.eventbus.hanlder.EventHandler;
import com.ruoxu.eventbus.hanlder.UIThreadEventHandler;

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


//     * 将接收方法执行在UI线程
    EventHandler mUIThreadEventHandler = new UIThreadEventHandler();

    public void dispatchEvents(ThreadLocal<Queue<EventType>> threadLocal,Object aEvent) {
        Queue<EventType> eventsQueue = threadLocal.get();
        while (eventsQueue.size() > 0) {
            handleEvent(eventsQueue.poll(), aEvent);//循环处理 每种EventType类型的list
        }
    }

    private void handleEvent(EventType eventType, Object aEvent) {

        List<Subscriber> subscriptions = mSubcriberMap.get(eventType);

        for (Subscriber subscriber : subscriptions) {
            mUIThreadEventHandler.handleEvent(subscriber,aEvent);
        }
    }


}
