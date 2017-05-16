package com.yulu.zhaoxinpeng.myrecyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yulu.zhaoxinpeng.myrecyclerviewdemo.view1.LinearActivity;
import com.yulu.zhaoxinpeng.myrecyclerviewdemo.view2.StaggereAdapter;
import com.yulu.zhaoxinpeng.myrecyclerviewdemo.view2.StaggeredActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.Staggere_recyclerview)
    Button StaggereRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.normal_recyclerview, R.id.Staggere_recyclerview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.normal_recyclerview:
                Intent intent1 = new Intent(MainActivity.this, LinearActivity.class);
                startActivity(intent1);
                break;
            case R.id.Staggere_recyclerview:
                Intent intent2 = new Intent(MainActivity.this, StaggeredActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
