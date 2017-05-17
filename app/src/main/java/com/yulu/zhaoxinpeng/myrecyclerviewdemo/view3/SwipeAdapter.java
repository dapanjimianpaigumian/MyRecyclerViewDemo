package com.yulu.zhaoxinpeng.myrecyclerviewdemo.view3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.yulu.zhaoxinpeng.myrecyclerviewdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/17.
 */
public class SwipeAdapter extends BaseSwipeAdapter {
    Context context;

    public SwipeAdapter(Context context) {
        this.context = context;
    }
    //---------------------------------BaseSwipeAdapter要实现的方法-------------------------------------------

    // 返回item的SwipeLayout的布局的id
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.item_swipe_layout;
    }

    //视图的初始化和操作
    @Override
    public View generateView(int position, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_swipelayout,null);

        SwipeLayout mSwipeLayout = (SwipeLayout) view.findViewById(getSwipeLayoutResourceId(position));
        //设置模式
        mSwipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //设置监听：可以直接new SwipeListener接口，也可以直接new SimpleSwipeListener，选择性的重写里面的方法
        mSwipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                super.onOpen(layout);
                Toast.makeText(context, "打开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose(SwipeLayout layout) {
                super.onClose(layout);
                Toast.makeText(context, "关闭了", Toast.LENGTH_SHORT).show();
            }
        });

        //设置双击的监听
        mSwipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(context, "双击了", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "删除了", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "收藏了", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView mTextView = (TextView) convertView.findViewById(R.id.tvShow);
        mTextView.setText("数据 "+position);

    }

    //----------------------------------------BaseAdapter要实现的方法------------------------------------------


    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return 40;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
