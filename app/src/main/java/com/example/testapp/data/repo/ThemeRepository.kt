package com.example.testapp.data.repo

import com.example.testapp.data.dao.ThemeDao
import com.example.testapp.data.model.Theme
import io.reactivex.rxjava3.core.Single

class ThemeRepository(val themeDao: ThemeDao) {

    fun getAllTheme(): Single<List<Theme>> {
        return themeDao.getAllTheme()
    }
}