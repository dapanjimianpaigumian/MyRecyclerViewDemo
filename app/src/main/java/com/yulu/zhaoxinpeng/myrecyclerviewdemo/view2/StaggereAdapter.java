package com.yulu.zhaoxinpeng.myrecyclerviewdemo.view2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yulu.zhaoxinpeng.myrecyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/16.
 */
public class StaggereAdapter extends RecyclerView.Adapter<StaggereAdapter.MyViewHolder>{

    /**
     *因为是瀑布流，所以数据的高是不固定的，所以我们要以随机数的形式给item设置高度
     */

    //数据
    private List<String> mData=new ArrayList<>();

    //高度
    private List<Integer> mHeights=new ArrayList<>();

    //设置数据
    public void setData(List<String> list,List<Integer> heights){
        mData.clear();
        mHeights.clear();
        mData.addAll(list);
        mHeights.addAll(heights);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggerde_recycler,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        //设置item的随机高度
        ViewGroup.LayoutParams layoutParams = holder.mTv.getLayoutParams();
        //更改高度参数
        layoutParams.height=mHeights.get(position);
        //再给item的 高度设置上
        holder.mTv.setLayoutParams(layoutParams);
        holder.mTv.setText(mData.get(position));

        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

        holder.mTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                onItemClickListener.onItemLongClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private OnItemClickListener onItemClickListener;

    //对外提供方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    //设置点击事件的接口回调
    protected interface OnItemClickListener{

        void onItemClick(int position);//短按

        void onItemLongClick(int position);//长按
    }
}
