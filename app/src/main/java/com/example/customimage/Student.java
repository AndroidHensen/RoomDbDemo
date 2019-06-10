package com.example.customimage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tb_student")
public class Student {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "sex")
    public int sex;

    @Embedded
    public StudentExtendInfo extendInfo;

    @Ignore
    public String phone;

    public static class StudentExtendInfo {
        @ColumnInfo(name = "father_name")
        public String father;
        @ColumnInfo(name = "mother_name")
        public String mother;
    }

}

