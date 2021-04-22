package com.example.homework_4

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDAO {

    @Query("SELECT * FROM dream_table ORDER BY id ASC")
    fun getAllDreams(): Flow<List<Dream>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dream: Dream)

    @Query("UPDATE dream_table SET title=:title, content=:content, reflection=:reflection, emotion=:emotion WHERE id=:id")
    suspend fun update(id:Int, title:String, content:String, reflection:String, emotion:String)

    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun deleteDream(id:Int)

    @Query ("SELECT * FROM dream_table WHERE id=:id")
    fun getDream(id:Int):Dream

}