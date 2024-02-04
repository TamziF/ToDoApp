package com.example.todo.data.datasource

import android.content.Context
import androidx.room.Room
import com.example.todo.data.database.Database

class DataSource(context: Context) {
    private val dataBase = Room
        .databaseBuilder(
            context,
            Database::class.java,
            "ToDoItemsDatabase"
        )
        .build()

    val dao = dataBase.dao()
}