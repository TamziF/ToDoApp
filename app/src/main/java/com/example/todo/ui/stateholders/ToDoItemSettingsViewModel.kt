package com.example.todo.ui.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.model.ToDoItem
import com.example.todo.data.repository.ToDoItemsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ToDoItemSettingsViewModel(
    private val repository: ToDoItemsRepository
) : ViewModel() {

    private val _toDoItem: MutableStateFlow<ToDoItem?> = MutableStateFlow(null)
    val toDoItem: StateFlow<ToDoItem?> = _toDoItem

    private var isNewItem = true

    private fun newToDoItem(item: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNewToDoItem(item)
        }
    }

    private fun updateToDoItem(item: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            println("hello")
            repository.updateToDoItem(item)
            println("hello")
        }
    }

    fun deleteToDoItem(item: ToDoItem) {
        if (!isNewItem) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteToDoItem(item)
            }
        }
    }

    fun saveItem(item: ToDoItem) {
        if (isNewItem)
            newToDoItem(item)
        else
            updateToDoItem(item)
    }

    fun setToDoItem(id: String) {
        isNewItem = false
        viewModelScope.launch(Dispatchers.IO) {
            _toDoItem.value = repository.getToDoItemWithId(id)
        }
    }
}