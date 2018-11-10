package com.bkrc.listview.custom;

import android.graphics.drawable.Drawable;

/**
 * 作者： 小白攻城狮 on 2018/9/21.
 * 作用：
 * 来源：
 */

class Customer {

    private String name;
    private int id;

    public Customer(int i, String s) {
        this.id=i;
        this.name=s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
