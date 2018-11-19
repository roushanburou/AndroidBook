package com.brkc.memopractice.listview;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/11/13
 * @update 添加更新的内容
 */
public interface NotifyListChangeListener {

    void notifyRemove(int position);

    void notifyInsert(int position);

    void notifyUpdate(int position, String data);
}
