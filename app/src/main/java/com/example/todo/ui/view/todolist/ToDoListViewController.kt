package com.example.todo.ui.view.todolist

import android.content.Context
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.constants.Constants
import com.example.todo.data.model.Importance
import com.example.todo.data.model.ToDoItem
import com.example.todo.databinding.FragmentToDoListBinding
import com.example.todo.ui.stateholders.ToDoListViewModel
import kotlinx.coroutines.launch

class ToDoListViewController(
    binding: FragmentToDoListBinding,
    private val context: Context,
    private val adapter: ToDoListAdapter,
    private val viewModel: ToDoListViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val fragment: ToDoListFragment
) {

    private val recyclerView: RecyclerView = binding.recyclerView
    private val newPostButton = binding.newPostButton

    fun setupViews() {
        setUpToDoList()
        bindNewPostButton()
    }

    private fun setUpToDoList() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            fragment.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.toDoItemsList.collect { list ->
                    adapter.submitList(list)
                }
            }
        }
    }

    private fun bindNewPostButton() {
        newPostButton.setOnClickListener {
            val action = ToDoListFragmentDirections.actionToDoListToToDoItemSettings()
            it.findNavController().navigate(action)
            //viewModel.newToDoItem(ToDoItem("Test", "Testing my cool architecture", Importance.HIGH))
        }
    }

}