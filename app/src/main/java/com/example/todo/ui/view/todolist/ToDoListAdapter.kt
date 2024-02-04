package com.example.todo.ui.view.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.todo.data.model.ToDoItem
import com.example.todo.databinding.ToDoItemBinding
import com.example.todo.ui.stateholders.ToDoListViewModel

class ToDoListAdapter(val viewModel: ToDoListViewModel, private val fragment: ToDoListFragment) :
    ListAdapter<ToDoItem, ToDoItemViewHolder>(
        ToDoItemDiffCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val binding = ToDoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoItemViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}