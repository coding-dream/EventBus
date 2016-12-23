package com.ruoxu.eventbus;

import java.util.List;

/**
 * Created by wangli on 16/12/23.
 */

//被观察者
public interface Observable<T> {

    void registerObserver(Subscriber subscriber);

    void unregisterObserver(Subscriber subscriber);

    void notifyDataChange();

    public void setDatas(List<T> datas);

    public List<T> getDatas();

}
