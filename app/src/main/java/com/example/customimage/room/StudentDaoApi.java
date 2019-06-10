package com.example.customimage.room;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.customimage.Student;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface StudentDaoApi {

    @Query("SELECT * FROM TB_STUDENT")
    Flowable<List<Student>> query();

    @Query("SELECT * FROM TB_STUDENT WHERE id IN (:ids)")
    Flowable<List<Student>> queryByIds(long[] ids);

    @Query("SELECT * FROM TB_STUDENT WHERE id = (:id) LIMIT 1")
    Flowable<Student> queryById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student... entities);

    @Delete
    void delete(Student entity);

    @Update
    void update(Student entity);

}
