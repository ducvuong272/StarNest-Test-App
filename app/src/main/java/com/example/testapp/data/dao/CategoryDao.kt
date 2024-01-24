package com.example.testapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.testapp.data.model.Category
import io.reactivex.rxjava3.core.Single

@Dao
interface CategoryDao {

    @Query("SELECT * from category")
    fun getAllCategory(): Single<List<Category>>
}