package com.example.testapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapp.data.AppDatabase.Companion.DB_VERSION
import com.example.testapp.data.dao.CategoryDao
import com.example.testapp.data.dao.ThemeDao
import com.example.testapp.data.model.Category
import com.example.testapp.data.model.Theme

@Database(entities = [Theme::class, Category::class], version = DB_VERSION)
abstract class AppDatabase: RoomDatabase() {

    abstract fun themeDao(): ThemeDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        const val DB_NAME = "TestAppDB"
        const val DB_VERSION = 3

        fun provideAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .createFromAsset("database/test_app_db.db")
                .fallbackToDestructiveMigration()
//                .addMigrations(MIGRATION_2_3)
                .build()
        }
    }
}