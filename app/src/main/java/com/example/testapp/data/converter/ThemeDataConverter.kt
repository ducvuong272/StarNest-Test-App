package com.example.testapp.data.converter

import androidx.room.TypeConverter
import com.example.testapp.data.model.Theme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ThemeDataConverter {

    @TypeConverter
    @JvmStatic
    fun stringToThemeData(value: String): Theme {
        return Gson().fromJson(value, Theme::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun themeToString(theme: Theme): String {
        return Gson().toJson(theme)
    }
}