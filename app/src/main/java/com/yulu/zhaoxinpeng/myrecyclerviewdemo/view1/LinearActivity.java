package com.yulu.zhaoxinpeng.myrecyclerviewdemo.view1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.yulu.zhaoxinpeng.myrecyclerviewdemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/16.
 * 利用RecyclerView实现 ListView效果
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
        for (int i = 'A'; i < 'z'; i++) {
            mList.add("" + (char) i);
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

        //水平布局
        //mRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        // GridLayoutManager 网格布局
        //mRecyclerview.setLayoutManager(new GridLayoutManager(this,4));

        // StaggeredGridLayoutManager 一般的瀑布流布局
        //mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));

        // 2. 如果想要添加item的添加和删除的动画,提供了一个可以直接使用的动画：DefaultItemAnimator
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        // 3. 如果想要设置，可以设置分割线,默认提供的：DividerItemDecoration，可以自定义，可以item布局里面自己去设置。
        //水平分割线
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //竖直分割线
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        // 4. 设置适配器
        mLinearAdapter = new LinearAdapter();
        mRecyclerview.setAdapter(mLinearAdapter);

        // 5. 关于item的事件:点击、长按，通过接口回调的方式实现
        mLinearAdapter.setOnItemClick(new LinearAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(LinearActivity.this, "click: 短" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(LinearActivity.this, "click: 长" + position, Toast.LENGTH_SHORT).show();
                //设置长按删除
                //mLinearAdapter.removeData(position);
            }
        });
        // 6. 关于拖动和滑动的处理：借助一个类ItemTouchHelper来完成
//        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
//
//            /**
//             * 方向：
//             * dragdirs：拖动的方向：UP、DOWN、START、END、LEFT、RIGHT
//             * swipedirs:滑动的方向：UP、DOWN、START、END、LEFT、RIGHT
//             * 设置为0的时候，表示没有此项功能。
//             */
//
//            // 拿到设置的移动方向：滑动的方向、拖动的方向
//            @Override
//            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//
//                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//                int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
//
//                return makeMovementFlags(dragFlags,swipeFlags);
//            }
//
//            // 拖动的事件处理
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            // 滑动的事件处理
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        };

        /**
         * 6.本次我们借助一个类simplecallback来完成拖动和滑动
         * simplecallback简易处理了一下callback的getMovementFlags,需要在构造方法的参数里面传入相应的拖拽和滑动的方向。
         * 括号中两个参数：1 可以向上或向下拖动
         *                2 向右滑动时删除
         */
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

            // 拖动事件处理
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                //获取拖动的ViewHolder的position
                int fromPosition = viewHolder.getAdapterPosition();
                //得到目标的ViewHolder的position
                int toPosition = target.getAdapterPosition();

                //向下移动
                if (fromPosition < toPosition) {
                    //分别把中间的这些item进行调换
                    for (int i = fromPosition; i < toPosition; i++) {
                        //数据的交换：通过集合工具类Collections
                        Collections.swap(mList, i, i + 1);
                    }
                } else {
                    //向上移动
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mList, i, i - 1);
                    }
                }

                //刷新一下数据
                mLinearAdapter.itemMoved(fromPosition, toPosition, mList);

                //表示点击被消费
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //滑动时删除
                int position = viewHolder.getAdapterPosition();
                mLinearAdapter.removeData(position);
            }
        };

        //创建TouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        //设置作用的RecyclerView
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        mLinearAdapter.addData(1);
    }
}
