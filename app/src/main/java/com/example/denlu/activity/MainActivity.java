package com.example.denlu.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denlu.R;
import com.example.denlu.bean.user;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //重新定义onActivity，选用startActivity启动注册界面，结束后返回onactivityresult
        //需要重新定义来接受数据
        super.onActivityResult(requestCode, resultCode, data);
        //启动时输入的请求码，返回的数据，携带数据的intent
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    final EditText loginUsername = findViewById(R.id.zhanghao);
                    //获得控件的控制
                    String returnUsername = data.getStringExtra("username");
                    //获得用户名
                    loginUsername.setText(returnUsername);
                    loginUsername.setSelection(returnUsername.length());
                    //写入用户名
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector.getDatabase();
        //创建数据库
        List<user> users= LitePal.findAll(user.class);
        //获得数据库中的值


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //隐藏了上侧边框
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        //隐藏了提示栏，美观


      Button button1 = (Button) findViewById(R.id.button_1);
       button1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
          startActivityForResult(intent, 2);
         }
        });

       //实现记住密码
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit=(EditText)findViewById(R.id.zhanghao);
        passwordEdit=(EditText) findViewById(R.id.mima);
        rememberPass=(CheckBox)findViewById(R.id.remember_pass);
        login=(Button)findViewById(R.id.button_2);
        //保存复选框状态
        boolean isRememember=pref.getBoolean("remember_password",false);
        //获得对应值
        if (isRememember){
            //将密码设置在文本框里
            String password=pref.getString("password","");
            String account=pref.getString("account","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }


       /* login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();

                List<user> users= LitePal.findAll(user.class);
                //导入数据库中储存的值
                EditText username = findViewById(R.id.zhanghao);
                EditText password2= findViewById(R.id.mima);
                //获得在登录界面输入的账号密码
                for (user user : users) {
                    if (user.getZhanghao().equals(username) && user.getMima().equals(password2.getText().toString())) {

                        //输入的和储存的值账号密码都相等就可以进入
                        editor=pref.edit();
                        if(rememberPass.isChecked()){
                            editor.putBoolean("remember_password",true);
                            editor.putString("account",account);
                            editor.putString("password",password);
                        }
                        editor.apply();
                        Intent intent=new Intent(MainActivity.this, listview.class);
                        startActivity(intent);
                        finish();
                     //   onDestroy();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"账号或密码错误！",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });*/

        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<user> users= LitePal.findAll(user.class);
                //导入数据库中储存的值
                EditText username = findViewById(R.id.zhanghao);
                EditText password = findViewById(R.id.mima);

                //获得在登录界面输入的账号
                for (user user : users) {
                    if (user.getZhanghao().equals(username.getText().toString()) && user.getMima().equals(password.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, listview.class);
                        startActivity(intent);
                        onDestroy();
                        //输入的和储存的值账号密码都相等就可以进入
                    } else {
                    }
                }Toast.makeText(MainActivity.this,"账号或密码错误！",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
