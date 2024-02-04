package com.example.todo.ioc.todoitemsettingsfragment

import com.example.todo.databinding.FragmentToDoItemSettingsBinding
import com.example.todo.ui.view.todoitemsettings.ToDoItemSettingsViewController

class ToDoItemSettingsFragmentViewComponent(
    binding: FragmentToDoItemSettingsBinding,
    fragmentComponent: ToDoItemSettingsFragmentComponent
) {
    val viewController = ToDoItemSettingsViewController(
        binding,
        fragmentComponent.viewModel,
        fragmentComponent.fragment
    )
}