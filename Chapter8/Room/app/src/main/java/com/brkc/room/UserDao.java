package com.brkc.room;

import android.arch.persistence.room.Dao;
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
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Update
    void update(User user);

    @Delete
    void delete(User... users);

    @Delete
    void delete(User user);

    @Insert
    void insert(User user);

    @Insert
    void insert(List<User> userLists);

    @Query("SELECT * FROM user WHERE id=:id")
    User getUser(int id);

    @Query("SELECT * FROM user")
    Cursor getUserCursor();

    @Query("SELECT * FROM user WHERE age=:age")
    List<User> getUsersByAge(int age);

    @Query("SELECT * FROM user WHERE age=:age LIMIT :max")
    List<User> getUsersByAge(int max, int... age);


}
