package com.example.customimage.room;

import android.content.Context;

import com.example.customimage.Student;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class StudentDao {

    private StudentDao() {

    }

    public static StudentDao getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final StudentDao sInstance = new StudentDao();
    }

    public Flowable<List<Student>> query(Context context) {
        return AppDatabaseBuilder.Companion.getInstance(context).getStudentDao().query();
    }

    public Flowable<List<Student>> queryByIds(Context context, long[] ids) {
        return AppDatabaseBuilder.Companion.getInstance(context).getStudentDao().queryByIds(ids);
    }

    public Flowable<Student> queryById(Context context, long id) {
        return AppDatabaseBuilder.Companion.getInstance(context).getStudentDao().queryById(id);
    }

    public Observable<Boolean> insert(final Context context, final Student student) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                AppDatabaseBuilder.Companion.getInstance(context).getStudentDao().insert(student);
                return true;
            }
        });
    }

    public Observable<Boolean> delete(final Context context, final Student student) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                AppDatabaseBuilder.Companion.getInstance(context).getStudentDao().delete(student);
                return true;
            }
        });
    }

    public Observable<Boolean> update(final Context context, final Student student) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                AppDatabaseBuilder.Companion.getInstance(context).getStudentDao().update(student);
                return true;
            }
        });
    }
}
