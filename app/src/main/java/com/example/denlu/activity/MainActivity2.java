package com.example.denlu.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denlu.R;
import com.example.denlu.bean.user;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //隐藏了上侧边框
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        //隐藏了提示栏，美观

        Button button4=(Button)findViewById(R.id.button_4) ;
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        Button button3=(Button)findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText zhanghao=findViewById(R.id.zhanghao);
                EditText mima=findViewById(R.id.mima);
                EditText queren=findViewById(R.id.queren);
                //获得了对两个控件的控制
                String inputUsername = zhanghao.getText().toString();
                String inputPassword = mima.getText().toString();
                String inputqueren = queren.getText().toString();
                //获得了输入的值，保存到了变量里
                user users = new user();
                //建立映射的对象
                //储存账号密码
                users.setZhanghao(inputUsername);
                users.setMima(inputPassword);
                users.save();//保存
                //传回账号
                if(inputqueren.equals(inputPassword)) {
                    Intent intent = new Intent();
                    //构建这个intent主要是用于传回数据
                    intent.putExtra("username", inputUsername);
                    //第一个参数是intent的名字，第二个参数是要传递的数据
                    setResult(RESULT_OK, intent);
                    //setResult专门用于活动之间传递数据，
                    finish();
                    //销毁当前活动
                }else{
                    Toast.makeText(MainActivity2.this,"确认密码有误！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}