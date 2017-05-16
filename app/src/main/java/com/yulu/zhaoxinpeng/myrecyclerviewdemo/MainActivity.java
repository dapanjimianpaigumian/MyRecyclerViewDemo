package com.yulu.zhaoxinpeng.myrecyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yulu.zhaoxinpeng.myrecyclerviewdemo.view1.LinearActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.normal_recyclerview)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, LinearActivity.class);
        startActivity(intent);
    }
}
