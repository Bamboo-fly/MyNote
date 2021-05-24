package com.example.denlu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denlu.R;
import com.example.denlu.bean.data;

import org.litepal.LitePal;

public class dispaly extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dispaly);

        final Button finish=findViewById(R.id.display_finish);
        final Button remove=findViewById(R.id.display_remove);
        final EditText name=findViewById(R.id.display_name);
        final EditText content=findViewById(R.id.display_content);
       //这四句定义了display的两个按钮和两个editText
        //接收传进来的数据，如果通过创建点进来的话没数据
        final data d=(data)getIntent().getSerializableExtra("name");
       //接口，name为空，代表没有输入，是点击创建按钮进入的
        if(d!=null)//如果d不为null说明是点击列表进来的，更新数据
        {
            //改变控件的数据
            name.setText(d.getName());
            content.setText(d.getContent());
        }
        //设置完成的点击事件。
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d!=null)//如果d不为null说明是点击列表进来的，改数据库
                {
                    String id=d.getId()+"";
                    d.setContent(content.getText().toString());
                    d.setName(name.getText().toString());
                    d.updateAll("id=?",id);
                    Intent intent = new Intent(dispaly.this, listview.class);
                    startActivity(intent);
                    finish();
                }
                else //如果d为空的话，说明是点添加进来的，增数据库
                {
                    if(!name.getText().toString().equals(""))//如果有名字才可以跳回列表
                    {
                        data d2=new data();
                        d2.setContent(content.getText().toString());
                        d2.setName(name.getText().toString());
                        d2.save();
                        //保存数据
                        Intent intent = new Intent(dispaly.this, listview.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
        //设置删除的点击事件。
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d!=null)//如果d不为null说明是点击列表进来的，删除数据库中数据。
                {
                    String id=d.getId()+"";
                    LitePal.deleteAll(data.class,"id=?",id);
                    Intent intent = new Intent(dispaly.this, listview.class);
                    startActivity(intent);
                    finish();
                }
                else //如果d为空的话，说明是点添加进来的，只需要返回即可
                {
                    Intent intent = new Intent(dispaly.this, listview.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
