package com.yulu.zhaoxinpeng.myrecyclerviewdemo.view1;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.MyViewHolder> {


    private List<String> mList = new ArrayList<>();

    //设置数据
    public void setData(List<String> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    //添加一条数据
    public void addData(int position){
        mList.add(position,"insert one");
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mList.size()-position);
    }

    //移除一条数据
    public void removeData(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mList.size()-position);
    }

    // 创建ViewHolder视图
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal_recycler, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    // 视图和数据进行绑定
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.mTextView.setText(mList.get(position));

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });

        holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                mOnItemClickListener.onItemLongClick(position);
                return true;
            }
        });
    }

    //item的数量
    @Override
    public int getItemCount() {
        return mList.size();
    }

    // 创建ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView mTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    //自定义点击事件的接口
    private OnItemClickListener mOnItemClickListener;

    //对外提供方法
    public void setOnItemClick(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }

    //利用接口回调定义两个接口：短按和长按
    public interface OnItemClickListener{

        //短按
        void onItemClick(int position);

        //长按
        void onItemLongClick(int position);
    }
}
