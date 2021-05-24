package com.example.denlu.bean;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class data extends LitePalSupport implements Serializable {
    String name;
    String content;
    int id;
    user user;

    public com.example.denlu.bean.user getUser() {
        return user;
    }

    public void setUser(com.example.denlu.bean.user user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
}
//在对象和数据库之间直接建立映射关系，使操作更加简便，不必使用SQL语言

//便签功能里，每一条便签需要有name，内容，以及一个用于分辨的id
//可以把数据库看作一张张data表汇聚而成
// 让类继承DataSupport是为了可以对表进行操作（增删改查）

//关于

