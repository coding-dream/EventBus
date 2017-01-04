package com.ruoxu.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ruoxu.eventbus.EventBus;
import com.ruoxu.eventbus.Subscriber;

public class MainActivity extends AppCompatActivity implements Subscriber{

    private Subscriber subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        subscriber = EventBus.getDefault().register(new Subscriber() {
            @Override
            public void receive(Object object) {
                Toast.makeText(MainActivity.this, subscriber + "Tom 收到消息了:" + object, Toast.LENGTH_SHORT).show();
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
        EventBus.getDefault().unregister(subscriber);
    }

    @Override
    public void receive(Object object) {
        Toast.makeText(MainActivity.this, subscriber + "Jam 收到消息了:" + object, Toast.LENGTH_SHORT).show();
    }



}
