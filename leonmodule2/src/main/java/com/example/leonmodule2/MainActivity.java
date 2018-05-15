package com.example.leonmodule2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by leon on 2018/5/15.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.tv);
        textView.setText(intent.getStringExtra("displayParam"));
    }
}
