package com.example.testapp.data.repo

import com.example.testapp.data.dao.CategoryDao
import com.example.testapp.data.model.Category
import io.reactivex.rxjava3.core.Single

class CategoryRepository(private val categoryDao: CategoryDao) {

    fun getAllCategory(): Single<List<Category>> {
        return categoryDao.getAllCategory()
    }
}