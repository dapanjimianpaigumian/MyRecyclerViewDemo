package com.yulu.zhaoxinpeng.myrecyclerviewdemo.view1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.yulu.zhaoxinpeng.myrecyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/16.
 */

public class LinearActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private List<String> mList;
    private LinearAdapter mLinearAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    //设置数据源
    private void initData() {
        mList = new ArrayList<>();
        for (int i='A';i<'z';i++){
            mList.add(""+(char)i);
        }
        mLinearAdapter.setData(mList);
    }

    private void initView() {
        /** 1. 设置布局管理器:展示的是什么样式的
         * 提供三种：StaggeredGridLayoutManager 瀑布流
         *          LinearLayoutManager 类似ListView
         *          GridLayoutManager  网格样式
         */
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        // 2. 如果想要添加item的添加和删除的动画,提供了一个可以直接使用的动画：DefaultItemAnimator
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        // 3. 如果想要设置，可以设置分割线,默认提供的：DividerItemDecoration，可以自定义，可以item布局里面自己去设置。
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        // 4. 设置适配器
        mLinearAdapter = new LinearAdapter();
        mRecyclerview.setAdapter(mLinearAdapter);

        // 5. 关于item的事件:点击、长按，通过接口回调的方式实现
        mLinearAdapter.setOnItemClick(new LinearAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(LinearActivity.this, "click: 短"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(LinearActivity.this, "click: 长"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
