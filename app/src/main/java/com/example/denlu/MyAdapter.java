package com.example.denlu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denlu.activity.dispaly;
import com.example.denlu.bean.data;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //在创建的适配器类继承recyclerview特有的adapter但必须定义泛型，
    // 泛型的参数基本上是类名加定义的优化（viewholder）的名
    private Context mContext;
    //Contex就是上下文的意思
    private List<data>  list;
    //mContext,就是引用适配器页面的Activity。list就是列表中要是显示的集合。
    public MyAdapter(Context mContext,List<data> list)
    {
        this.mContext=mContext;
        this.list=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //绑定recyclerview布局里面的控件，这里只有显示名字的textview
        TextView textView;//textview保存了来保存最外层布局的实例，用于注册点击事件时使用
        //这里适配器和recycle布局开始联系起来
        public ViewHolder( @NonNull View itemView) {
            super(itemView);
           // cardView=itemView.findViewById(R.id.card);
           textView=itemView.findViewById(R.id.recycler_text);
        }
    }
    //需要重写adaptoir的三个函数onCreateViewHolder、onBindViewHolder、getItemCount()
    @NonNull
    @Override
    //这个方法用于创建ViewHolder实例
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //新建view，第一个参数为Context，添之前传进来的就行，第二个为传绑定的layout，
        //格式为R.alyout.layout的名，这里的名字是layout文件夹里的名字，去掉.xml之后的，第三个添null就可以了
        View view=View.inflate(mContext,R.layout.recyclerview,null);
        ViewHolder viewHolder=new ViewHolder(view);
        //创建实例，把加载进来的view布局传递进来，再把viewholder返回
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //绑定数据
        final data d = list.get(position);
        //position参数得到当前项的用户点击位置
        holder.textView.setText(d.getName());
        //设置textview 的点击事件，
        holder.textView.setOnClickListener(new View.OnClickListener() {
            //设置点击事件
            @Override
            public void onClick(View view) {
                //定义一个intent用于从
                Intent intent=new Intent(mContext, dispaly.class);//传入上下文和打开新的display界面
                intent.putExtra("name",d);//d就是获得的便签名
                // 这里面用到了Intent来传输数据，只要调用intent的putExtra方法就可以了，
                //putextra
                mContext.startActivity(intent);
                //  Context就可以调用startActivity方法来实现跳转的功能，而要想关闭当前页面，还必须得把它强转为Activity
                Activity main=(Activity)mContext;
                //   * Context就可以调用startActivity方法来实现跳转的功能，而要想关闭当前页面，
                //   还必须得把它强转为Activity
                main.finish();
            }
        });
    }
    //这个方法返回的是列表里面有多少项。返回list的长度就行啦
    @Override
    public int getItemCount() {

        return list.size();
    }
}
