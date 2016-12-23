package com.ruoxu.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ruoxu.eventbus.Observable;
import com.ruoxu.eventbus.ObservableImp;
import com.ruoxu.eventbus.SubscribeImp;
import com.ruoxu.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * 初级版本的观察者模式，不支持组件间通信，此Demo仅用来展示观察者模式实现
 * 
 */
public class MainActivity extends AppCompatActivity {
    Observable observable ;

    List<Subscriber> subscribers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        observable = new ObservableImp();

        Subscriber subscriber;

        for(int i=0;i<10;i++) {
            subscriber = new SubscribeImp("name"+i);

            subscribers.add(subscriber);
            observable.registerObserver(subscriber);
        }

        List<String> list = new ArrayList<>();
        list.add("bean1");
        list.add("bean2");

        observable.setDatas(list);
        observable.notifyDataChange();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //批量解注册
        for (Subscriber subscriber : subscribers) {
            observable.unregisterObserver(subscriber);
        }
    }
}
