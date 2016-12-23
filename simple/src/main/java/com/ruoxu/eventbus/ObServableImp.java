package com.ruoxu.eventbus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangli on 16/12/23.
 */
public class ObservableImp<T> implements Observable {

    private List<T> datas;

    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void registerObserver(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unregisterObserver(Subscriber subscriber) {
        if (subscribers.indexOf(subscriber) >= 0) {
            subscribers.remove(subscriber);
        }
    }

    @Override
    public void notifyDataChange() {

        for(int i=0;i<subscribers.size();i++) {
            Subscriber subscriber = subscribers.get(i);
            subscriber.receive();
        }
    }

    @Override
    public void setDatas(List datas) {
        this.datas = datas;
    }

    @Override
    public List getDatas() {
        return datas;
    }


}
