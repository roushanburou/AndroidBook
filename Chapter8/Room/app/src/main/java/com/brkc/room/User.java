package com.brkc.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 描述：这里需要使用@Entity来注解该类
         至少要有一个主键@PrimaryKey
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/10/13
 * @update 添加更新的内容
 */
@Entity
public class User {

    @PrimaryKey(autoGenerate = true)//主键是否自动增长，默认为false
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //这里的getter/setter方法是必须的
    //这里的getter/setter方法是必须的
    //这里的getter/setter方法是必须的
    //重要的事说三遍
}
