package com.example.todo.ui.view.todolist

import androidx.recyclerview.widget.DiffUtil
import com.example.todo.data.model.ToDoItem


class ToDoItemDiffCallBack : DiffUtil.ItemCallback<ToDoItem>() {
    override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
        return (oldItem.name == newItem.name &&
                oldItem.importance == newItem.importance &&
                oldItem.deadLine == newItem.deadLine &&
                oldItem.creationDate == newItem.creationDate &&
                oldItem.description == newItem.description &&
                oldItem.isDone == newItem.isDone)
    }
}