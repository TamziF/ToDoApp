package com.example.todo.ui.view.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.todo.App
import com.example.todo.constants.Constants
import com.example.todo.data.model.ToDoItem
import com.example.todo.databinding.FragmentToDoListBinding
import com.example.todo.ioc.todolistfragment.ToDoListFragmentComponent
import com.example.todo.ioc.todolistfragment.ToDoListFragmentViewComponent
import com.example.todo.ui.stateholders.ToDoListViewModel

class ToDoListFragment : Fragment() {

    private lateinit var binding: FragmentToDoListBinding

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: ToDoListFragmentComponent
    private var fragmentViewComponent: ToDoListFragmentViewComponent? = null
    private val viewModel: ToDoListViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = ToDoListFragmentComponent(
            viewModel,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentToDoListBinding.inflate(layoutInflater, container, false)

        fragmentViewComponent = ToDoListFragmentViewComponent(
            requireContext(),
            fragmentComponent,
            binding,
            viewLifecycleOwner
        ).apply {
            toDoListViewController.setupViews()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        fragmentViewComponent = null
    }
}