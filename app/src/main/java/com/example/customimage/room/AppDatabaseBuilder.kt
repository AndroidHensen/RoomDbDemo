package com.example.customimage.room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.example.customimage.BuildConfig
import com.example.customimage.Student

@Database(entities = [Student::class], version = 1)
abstract class AppDatabaseBuilder : RoomDatabase() {

    abstract val studentDao: StudentDaoApi

    companion object {
        private var INSTANCE: AppDatabaseBuilder? = null

        fun getInstance(context: Context): AppDatabaseBuilder {
            if (INSTANCE == null) {
                synchronized(AppDatabaseBuilder::class.java) {
                    val builder = Room.databaseBuilder(context.applicationContext,
                            AppDatabaseBuilder::class.java, "db_common.db")
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)

                    if (!BuildConfig.DEBUG) {
                        //迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                        builder.fallbackToDestructiveMigration()
                    }
                    INSTANCE = builder.build()
                }
            }
            return INSTANCE!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'tb_student' ADD COLUMN 'Sid' INTEGER NOT NULL DEFAULT 0")
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'tb_student' ADD COLUMN 'Stext' TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}