package com.example.denlu.bean;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class user extends LitePalSupport implements Serializable {
    private String zhanghao;
    private String mima;

    private List<data> dataList = new ArrayList<data>();
    //data专门用来建立两张表之间的关联

    public List<data> getDataList() {
        return dataList;
    }

    public void setDataList(List<data> dataList) {
        this.dataList = dataList;
    }

    public String getZhanghao() {
        return zhanghao;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }
}
