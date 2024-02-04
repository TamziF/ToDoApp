package com.example.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.model.ToDoItem

@Database(version = 1, entities = [ToDoItem::class],)
abstract class Database: RoomDatabase() {
    abstract fun dao(): Dao
}