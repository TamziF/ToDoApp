package com.example.todo.ui.view.todoitemsettings

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.todo.data.model.ToDoItem
import com.example.todo.databinding.FragmentToDoItemSettingsBinding
import com.example.todo.ui.stateholders.ToDoItemSettingsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ToDoItemSettingsViewController(
    binding: FragmentToDoItemSettingsBinding,
    private val viewModel: ToDoItemSettingsViewModel,
    private val fragment: ToDoItemSettingsFragment
) {

    private val nameTextView = binding.nameTv
    private val descriptionTextView = binding.descriptionTv
    private val deadlineTextView = binding.deadlineTv
    private val importanceView = binding.importanceChoosingView
    private val saveTextView = binding.saveTv


    fun setupViews() {
        bindSaveTextView()
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            fragment.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.toDoItem.collect { item -> item?.let { bindInfoViews(it) } }
            }
        }
    }

    private fun bindInfoViews(item: ToDoItem) {
        nameTextView.setText(item.name)
        descriptionTextView.setText(item.description)
        deadlineTextView.text = item.deadLine
        importanceView.setImportance(item.importance)
    }

    private fun bindSaveTextView() {
        saveTextView.setOnClickListener {
            viewModel.saveItem(buildItem())
            val action = ToDoItemSettingsFragmentDirections.actionToDoItemSettingsToToDoList()
            it.findNavController().navigate(action)
        }
    }

    private fun buildItem(): ToDoItem {
        return ToDoItem(
            nameTextView.text.toString(),
            descriptionTextView.text.toString(),
            importanceView.getImportance()
        )
    }
}