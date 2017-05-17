package com.yulu.zhaoxinpeng.myrecyclerviewdemo.view3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.yulu.zhaoxinpeng.myrecyclerviewdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipeActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView mListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        ButterKnife.bind(this);

        SwipeAdapter mSwipeAdapter = new SwipeAdapter(this);
        mListview.setAdapter(mSwipeAdapter);
    }
}
