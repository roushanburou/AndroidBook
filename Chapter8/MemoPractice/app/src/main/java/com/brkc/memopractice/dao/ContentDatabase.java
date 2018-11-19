package com.brkc.memopractice.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/10/13
 * @update 添加更新的内容
 */
@Database(entities = { Content.class }, version = 1,exportSchema = false)
public abstract class ContentDatabase extends RoomDatabase {

    private static final String DB_NAME = "ContentDatabase.db";
    private static volatile ContentDatabase instance;

    public static synchronized ContentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static ContentDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                ContentDatabase.class,
                DB_NAME).allowMainThreadQueries()
                .build();
    }

    public abstract ContentDao getContentDao();
}
