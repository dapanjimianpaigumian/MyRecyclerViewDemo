package com.yulu.zhaoxinpeng.myrecyclerviewdemo.view2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.yulu.zhaoxinpeng.myrecyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaggeredActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private StaggereAdapter mStaggereAdapter;
    private List<String> mList;
    private List<Integer> mHeights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        mHeights = new ArrayList<>();
        for (int i = 'A'; i <'z'; i++) {
            mList.add(""+(char)i);
            mHeights.add((int) (Math.random()*400+200));
        }
        mStaggereAdapter.setData(mList,mHeights);
    }

    private void initView() {
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        //水平分割线
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //竖直分割线
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        //设置适配器
        mStaggereAdapter = new StaggereAdapter();

        mRecyclerview.setAdapter(mStaggereAdapter);
        mStaggereAdapter.setOnItemClickListener(new StaggereAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(StaggeredActivity.this, "click:短"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(StaggeredActivity.this, "click:长"+position, Toast.LENGTH_SHORT).show();
            }


    });
}
}