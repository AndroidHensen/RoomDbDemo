package com.example.customimage

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.customimage.room.AppDatabaseBuilder
import com.example.customimage.room.StudentDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : Activity(), View.OnClickListener {

    private var lv: ListView? = null
    private var adapter: StudentAdapter? = null
    private var bt_add: Button? = null
    private var bt_delete: Button? = null
    private val compositeDisposable = CompositeDisposable()
    private val list = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lv = findViewById(R.id.lv)
        bt_add = findViewById(R.id.bt_add)
        bt_delete = findViewById(R.id.bt_delete)
        bt_add!!.setOnClickListener(this)
        bt_delete!!.setOnClickListener(this)

        queryAllStudent()

        adapter = StudentAdapter(this, list)
        lv!!.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun queryAllStudent() {
        compositeDisposable.add(AppDatabaseBuilder.getInstance(this).studentDao.query()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ students ->
                    list.clear()
                    list.addAll(students)
                    adapter!!.notifyDataSetChanged()
                }, { throwable -> Log.e("TAG", throwable.toString()) }))
    }

    override fun onClick(v: View) {
        when (v.id) {
            //添加学生
            R.id.bt_add -> {
                val student = Student()
                val extendInfo = Student.StudentExtendInfo()
                student.name = "俊俊俊.apk"
                student.sex = Random().nextInt(3)
                extendInfo.father = "许潇洒"
                extendInfo.mother = "许美丽"
                student.extendInfo = extendInfo

                StudentDao.getInstance().insert(this, student)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                { Log.e("TAG", it.toString()) },
                                { Log.e("TAG", it.toString()) }
                        )
            }
            //删除学生
            R.id.bt_delete -> {
                if (list.size > 0) {
                    StudentDao.getInstance().delete(this, list[0])
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    { Log.e("TAG", it.toString()) },
                                    { Log.e("TAG", it.toString()) }
                            )
                }
            }
        }
        queryAllStudent()
    }

}
