package com.example.todo.ui.view.todolist

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.constants.Constants
import com.example.todo.data.model.ToDoItem
import com.example.todo.databinding.ToDoItemBinding

class ToDoItemViewHolder(private val binding: ToDoItemBinding, private val fragment: ToDoListFragment) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ToDoItem) {
        binding.radioButton.isChecked = item.isDone
        binding.textTv.text = item.name
        binding.deadlineTitle.text = item.description
        //TODO добавить контроль размера текста для подстановки многоточия "..."

        binding.item.setOnClickListener {
            fragment.setFragmentResult(Constants.ITEM_ID_REQUEST, bundleOf(Constants.ITEM_ID_REQUEST to item.id))
            val action = ToDoListFragmentDirections.actionToDoListToToDoItemSettings()
            it.findNavController().navigate(action)
        }
    }

}
