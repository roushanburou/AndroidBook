package com.brkc.memopractice.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

/**
 * 描述：接下来，需要为我们的实体创建DAO。 DAO代表数据访问对象，所以它是告诉我们的数据库如何操作数据的一种方式：
 *      使用@Dao注解该接口
        @Insert, @Update, @Delete,@Query代表我们常用的插入、更新、删除、查询数据库操作
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/10/13
 * @update 添加更新的内容
 */
@Dao
public interface ContentDao {
    @Query("SELECT * FROM Content")
    List<Content> getAllContents();

    @Insert
    void insert(Content... contents);

    @Update
    void update(Content... contents);

    @Update
    void update(Content content);

    @Delete
    void delete(Content... contents);

    @Delete
    void delete(Content content);

    @Insert
    void insert(Content content);

    @Insert
    void insert(List<Content> contents);

    @Query("SELECT * FROM Content WHERE id=:id")
    Content getContent(int id);

    @Query("SELECT * FROM Content")
    Cursor getContentCursor();

    @Query("SELECT * FROM Content WHERE isCheck=:isCheck")
    List<Content> getContentsByCheck(boolean isCheck);

    @Query("DELETE FROM Content")
    public void deleteAll();
}
