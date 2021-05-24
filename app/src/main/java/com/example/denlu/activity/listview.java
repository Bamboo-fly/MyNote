package com.example.denlu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.denlu.MyAdapter;
import com.example.denlu.R;
import com.example.denlu.bean.data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.litepal.LitePal;

public class listview extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    //用于加载信息界面的控件，最左则的图标
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        //加载菜单文本
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        //定义与绑定控件,在布局中仅含有button和recycleview
        //findviewByid是定位函数，用于找到button,recycle
        FloatingActionButton button2=findViewById(R.id.fab);
      //  Button button=findViewById(R.id.createButton);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        //定义布局管理，这个是必须的，如果要让recyclerview显示出来，必须得为他配置布局与适配器两个东西。
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //定义适配器
        MyAdapter adapter=new MyAdapter(this, LitePal.findAll(data.class));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        
        //定义创建按钮得点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent的第一个参数是当前页面，第二个为跳转后的页面
                Intent intent=new Intent(listview.this, dispaly.class);
                startActivity(intent);
                //关闭当前页面
                finish();
            }
        });


       //下面是自定义标题栏部分
        Toolbar toolbar=(Toolbar)findViewById(R.id.tooler);
        //获得了toolbar实例
        setSupportActionBar(toolbar);
        //展开toolbar实例
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        //获得DrawerLayout实例
        ActionBar actionBar=getSupportActionBar();
        //获得toolbar实例

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            //该方法显示出导航栏
            actionBar.setHomeAsUpIndicator(R.drawable.usercenter);
            //该方法确定了最左侧的图标
        }

        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_call);
        //设置为默认选中
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override//设置菜单选项的监听事件
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_call:
                        Intent intent3=new Intent(listview.this, information.class);
                        startActivity(intent3);
                        break;
                    default:
                }
                return true;

            }
        });
    }

    @Override
    //利用这个方法确定每一个按键的点击事件
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                //使侧滑菜单得以展现
                break;
            case R.id.fanhui:
                Intent intent=new Intent(listview.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }



}
