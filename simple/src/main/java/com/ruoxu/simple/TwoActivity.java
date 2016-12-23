package com.ruoxu.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruoxu.eventbus.EventBus;

public class TwoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);


        EventBus.getDefault().notifyDataChange("hellojava");
        EventBus.getDefault().notifyDataChange("hellojava","tag1");

        EventBus.getDefault().notifyDataChange(null); //事件不能为null
    }
}
