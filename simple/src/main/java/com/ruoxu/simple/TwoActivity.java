package com.ruoxu.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ruoxu.eventbus.EventBus;

public class TwoActivity extends AppCompatActivity {


    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        EventBus.getDefault().notifyDataChange("hellojava");
        EventBus.getDefault().notifyDataChange("hellojava","tag1");


        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().notifyDataChange("Hello Android");
            }
        });

    }
}
