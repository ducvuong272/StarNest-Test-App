package com.example.testapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
class Category(
    @PrimaryKey
    val id: String,

    val name: String
)

const val GALAXY_CATEGORY = "Galaxy"
const val ANIMAL_CATEGORY = "Animal"
const val FUNNY_CATEGORY = "Funny"
