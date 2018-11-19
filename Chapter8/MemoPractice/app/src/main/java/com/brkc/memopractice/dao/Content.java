package com.brkc.memopractice.dao;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/13
 * @update 添加更新的内容
 */
@Entity
public class Content {

    @PrimaryKey(autoGenerate = true)//主键是否自动增长，默认为false
    private int id;
    private boolean isCheck;
    private String content;

    public Content(boolean isCheck, String content) {
        this.isCheck = isCheck;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Content{" +
                "isCheck=" + isCheck +
                ", content='" + content + '\'' +
                '}';
    }
}
