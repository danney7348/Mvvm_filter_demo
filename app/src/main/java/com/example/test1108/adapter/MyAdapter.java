package com.example.test1108.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/11/8.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyAdapter<T> extends RecyclerView.Adapter<MyAdapter.MyHolder>{
    private Context context;
    private List<T> list;
    private int layoutId;
    private int vid;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<T> list, int layoutId, int vid) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
        this.vid = vid;
        inflater = LayoutInflater.from(context);
    }

    public void refresh(List<T> list1){
        if(list != null){
            list.clear();
            list.addAll(list1);
            notifyDataSetChanged();
        }
    }
    public void loadmore(List<T> list1){
        if(list != null){
            list.addAll(list1);
            notifyDataSetChanged();
        }
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,layoutId,parent,false);
        MyHolder holder = new MyHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyHolder holder, int position) {

        holder.binding.setVariable(vid,list.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;
        public MyHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }
}
