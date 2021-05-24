package com.example.denlu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denlu.R;

public class information extends AppCompatActivity {

    private EditText user_input;
    private EditText youxiang_input;
    private TextView user_save;
    private TextView youxiang_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        user_input=(EditText)findViewById(R.id.zz);
        youxiang_input=(EditText)findViewById(R.id.youxiang2);
        user_save=(TextView) findViewById(R.id.username);
        youxiang_save=(TextView) findViewById(R.id.youxiang);

        Button button6=(Button)findViewById(R.id.querenxinxi);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(information.this, listview.class);
                startActivity(intent);
            }
        });

    }

    public void saveandload(View v) {
        //要保存的数据
        String user= user_input.getText().toString();
        String mail=youxiang_input.getText().toString();
        //获取SharedPreferences（关联共享）对象
        SharedPreferences sp = getSharedPreferences("dataSp",
                Context.MODE_PRIVATE);
        // 编辑器
        SharedPreferences.Editor editor = sp.edit();
        //利用编辑器存储数据，利用键值对形式存储
        editor.putString("name", user);
        editor.putString("mail", mail);
        // 提交，利用commit提交有返回值，editor.apply()提交没有返回值
        boolean flag = editor.commit();
        if (flag) {// 如果保存成功则给出成功的提示
            Toast.makeText(information.this, "保存成功！",
                    Toast.LENGTH_LONG).show();
        }
        Log.d("zz",user);
        Log.d("mm",mail);
        //获取SharedPreferences（关联共享）对象
        SharedPreferences sp2 = getSharedPreferences("dataSp",
                Context.MODE_PRIVATE);
        //获取SharedPreferences中的数据
        String name=sp2.getString("name", "");
        String mail2=sp2.getString("age", "");
        Log.d("xx",user);
        Log.d("zc",mail2);
        user_save.setText(name);
        youxiang_save.setText(mail2);
    }

}
