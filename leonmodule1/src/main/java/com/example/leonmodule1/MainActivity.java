package com.example.leonmodule1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.business.bus.Bus;

/**
 * Created by leon on 2018/5/15.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        TextView one = (TextView) findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bus.callData(MainActivity.this, "leonmodule2/test","我是从module1中传过来的参数");
            }
        });
    }
}
