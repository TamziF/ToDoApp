package com.example.todo.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.data.model.ToDoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM ToDoItems")
    fun loadToDoItemsFlow(): Flow<List<ToDoItem>>

    @Insert
    suspend fun insert(item: ToDoItem)

    @Update
    suspend fun update(item: ToDoItem)

    @Delete
    suspend fun delete(item: ToDoItem)

    @Query("SELECT * FROM ToDoItems WHERE id = :id")
    suspend fun getItemWithId(id: String): ToDoItem
}