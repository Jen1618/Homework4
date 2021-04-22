package com.example.homework_4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="dream_table")
class Dream(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = 0,
            @ColumnInfo(name = "title") val title:String,
            @ColumnInfo(name = "content") val content:String,
            @ColumnInfo(name = "reflection") val reflection:String,
            @ColumnInfo(name = "emotion") val emotion:String)