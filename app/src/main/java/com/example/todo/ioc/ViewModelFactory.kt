package com.example.todo.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.repository.ToDoItemsRepository
import com.example.todo.ui.stateholders.ToDoItemSettingsViewModel
import com.example.todo.ui.stateholders.ToDoListViewModel

class ViewModelFactory(private val toDoItemsRepository: ToDoItemsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        ToDoListViewModel::class.java -> ToDoListViewModel(toDoItemsRepository)
        ToDoItemSettingsViewModel::class.java -> ToDoItemSettingsViewModel(toDoItemsRepository)
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}