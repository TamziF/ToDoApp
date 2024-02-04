package com.example.todo.data.repository

import com.example.todo.data.datasource.DataSource
import com.example.todo.data.model.ToDoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList

class ToDoItemsRepository(private val dataSource: DataSource) {

    private val _itemsList: MutableStateFlow<ArrayList<ToDoItem>> = MutableStateFlow(ArrayList())
    val itemsList: StateFlow<List<ToDoItem>> = _itemsList

    fun loadToDoItems(): Flow<List<ToDoItem>>{
        return dataSource.dao.loadToDoItemsFlow()
    }

    suspend fun insertNewToDoItem(item: ToDoItem){
        dataSource.dao.insert(item)
        _itemsList.value.add(item)
    }

    suspend fun updateToDoItem(item: ToDoItem){
        dataSource.dao.update(item)
        updateItem(item)
    }

    private fun updateItem(item: ToDoItem){
        var i = 0
        while(i < _itemsList.value.size){
            if(_itemsList.value[i] == item) {
                _itemsList.value[i] = item
                break
            }
            i++
        }
    }

    suspend fun deleteToDoItem(item: ToDoItem){
        dataSource.dao.delete(item)
        _itemsList.value.remove(item)
    }

    suspend fun getToDoItemWithId(id: String): ToDoItem{
        return dataSource.dao.getItemWithId(id)
    }

}