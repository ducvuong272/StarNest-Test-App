package com.example.testapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.testapp.data.model.Theme
import io.reactivex.rxjava3.core.Single

@Dao
interface ThemeDao {

    @Query("SELECT * from theme")
    fun getAllTheme(): Single<List<Theme>>
}