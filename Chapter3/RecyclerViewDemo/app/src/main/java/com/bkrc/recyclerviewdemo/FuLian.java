package com.bkrc.recyclerviewdemo;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/10/27
 * @update 添加更新的内容
 */
public class FuLian {

    private String name;
    private int ImageResource;

    public FuLian(String name, int imageResource) {
        this.name = name;
        ImageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }
}
