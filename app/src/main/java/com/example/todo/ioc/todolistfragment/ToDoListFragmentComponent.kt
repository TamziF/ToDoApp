package com.example.todo.ioc.todolistfragment

import com.example.todo.ui.stateholders.ToDoListViewModel
import com.example.todo.ui.view.todolist.ToDoListAdapter
import com.example.todo.ui.view.todolist.ToDoListFragment

class ToDoListFragmentComponent(
    val viewModel: ToDoListViewModel,
    val fragment: ToDoListFragment
) {
    val adapter = ToDoListAdapter(viewModel, fragment)
}