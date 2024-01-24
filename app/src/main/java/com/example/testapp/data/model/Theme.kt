package com.example.testapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "theme")
data class Theme(
    @PrimaryKey
    val id: String,

    val categoryId: String,

    val type: String,

    val icon: String
): Parcelable