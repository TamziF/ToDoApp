package com.example.todo.ioc.todolistfragment

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.todo.databinding.FragmentToDoListBinding
import com.example.todo.ui.view.todolist.ToDoListViewController

class ToDoListFragmentViewComponent(
    context: Context,
    fragmentComponent: ToDoListFragmentComponent,
    binding: FragmentToDoListBinding,
    lifecycleOwner: LifecycleOwner
) {
    val toDoListViewController = ToDoListViewController(
        binding,
        context,
        fragmentComponent.adapter,
        fragmentComponent.viewModel,
        lifecycleOwner,
        fragmentComponent.fragment
    )
}