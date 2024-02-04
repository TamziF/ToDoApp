package com.example.todo.ui.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.model.ToDoItem
import com.example.todo.data.repository.ToDoItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ToDoListViewModel(
    private val repository: ToDoItemsRepository
): ViewModel() {

    private var toDoItem: ToDoItem? = null

    private val _toDoItemsList: MutableStateFlow<List<ToDoItem>> = MutableStateFlow(emptyList())
    val toDoItemsList: StateFlow<List<ToDoItem>> = _toDoItemsList

    init{
        loadToDoItems()
    }

    fun newToDoItem(item: ToDoItem){
        val job = viewModelScope.launch(Dispatchers.IO){
            repository.insertNewToDoItem(item)
        }
        job.invokeOnCompletion{
            loadToDoItems()
        }
    }

    fun updateToDoItem(item: ToDoItem){
        val job = viewModelScope.launch(Dispatchers.IO) {
            repository.updateToDoItem(item)
        }
        job.invokeOnCompletion{
            loadToDoItems()
        }
    }

    fun deleteToDoItem(item: ToDoItem){
        val job = viewModelScope.launch(Dispatchers.IO) {
            repository.deleteToDoItem(item)
        }
        job.invokeOnCompletion {
            loadToDoItems()
        }
    }

    private fun loadToDoItems(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadToDoItems().collect { list -> _toDoItemsList.value = list }
        }
    }
}