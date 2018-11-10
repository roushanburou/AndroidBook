package com.bkrc.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/10/27
 * @update 添加更新的内容
 */
class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder>{

    ArrayList<FuLian> list;

    public RecyclerAdapter(ArrayList<FuLian> list){
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        final Holder holder = new Holder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FuLian fuLian = list.get(position);
                Toast.makeText(v.getContext(), "点击外框：" + fuLian.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FuLian fuLian = list.get(position);
                Toast.makeText(v.getContext(), "点击文字：" + fuLian.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    /**
     * 这里是我们操作item的地方
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String name = list.get(position).getName();
        holder.textView.setText(name);

        int image = list.get(position).getImageResource();
        holder.imageView.setImageResource(image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        View view;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
