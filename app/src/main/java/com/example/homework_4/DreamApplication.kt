package com.example.homework_4

import android.app.Application

class DreamApplication : Application(){
    // create 1 instance of database and repository
    val database by lazy {
        DreamRoomDatabase.getDatabase(this)
    }
    val repository by lazy{
        DreamRepository(database.dreamDAO())
    }
}