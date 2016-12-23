package com.ruoxu.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ruoxu.eventbus.EventBus;
import com.ruoxu.eventbus.Subscriber;

public class MainActivity extends AppCompatActivity implements Subscriber{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        EventBus.getDefault().register(this);

        EventBus.getDefault().register(new Subscriber() {
            @Override
            public void receive(Object object) {
                Log.d("MainActivity", "EventBus.getDefault() 收到消息了:" + object);
            }
        },"tag1");




        Intent intent = new Intent();
        intent.setClass(this, TwoActivity.class);
        startActivity(intent);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);

    }

    @Override
    public void receive(Object object) {
        Log.d("MainActivity", "收到消息了"+object);
    }



}
