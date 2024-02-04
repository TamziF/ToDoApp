package com.example.todo.ioc

import android.content.Context
import com.example.todo.data.datasource.DataSource
import com.example.todo.data.repository.ToDoItemsRepository

class ApplicationComponent(context: Context) {
    private val dataSource = DataSource(context)
    private val repository = ToDoItemsRepository(dataSource)

    val viewModelFactory: ViewModelFactory = ViewModelFactory(repository)
}