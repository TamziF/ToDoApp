package com.example.todo.ui.view.todoitemsettings

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
import com.example.todo.databinding.FragmentToDoItemSettingsBinding
import com.example.todo.ioc.todoitemsettingsfragment.ToDoItemSettingsFragmentComponent
import com.example.todo.ioc.todoitemsettingsfragment.ToDoItemSettingsFragmentViewComponent
import com.example.todo.ui.stateholders.ToDoItemSettingsViewModel
import com.google.android.material.datepicker.MaterialDatePicker

class ToDoItemSettingsFragment : Fragment() {

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: ToDoItemSettingsFragmentComponent
    private var fragmentViewComponent: ToDoItemSettingsFragmentViewComponent? = null
    private val viewModel: ToDoItemSettingsViewModel by viewModels { applicationComponent.viewModelFactory }
    private lateinit var binding: FragmentToDoItemSettingsBinding


    private val datePicker: MaterialDatePicker<Long> by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = ToDoItemSettingsFragmentComponent(
            viewModel,
            this
        )

        setFragmentResultListener(Constants.ITEM_ID_REQUEST) { requestKey, bundle ->
            bundle.getString(Constants.ITEM_ID_REQUEST)?.let { viewModel.setToDoItem(it) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentToDoItemSettingsBinding.inflate(layoutInflater, container, false)

        fragmentViewComponent = ToDoItemSettingsFragmentViewComponent(
            binding,
            fragmentComponent
        ).apply {
            viewController.setupViews()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentViewComponent = null
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveTv.setOnClickListener {
            if (toDoItem == null) {
                viewModel.newToDoItem(createNewItem())
            } else {
                viewModel.updateToDoItem(updateTempItem())
            }
            val action = ToDoItemSettingsDirections.actionToDoItemSettingsToToDoList()
            it.findNavController().navigate(action)
        }

        binding.showCalendarSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.deadlineTv.visibility = View.VISIBLE

                val fragmentManager: FragmentManager by lazy {
                    (requireActivity() as AppCompatActivity).supportFragmentManager
                }

                datePicker.show(fragmentManager, "")

                datePicker.addOnPositiveButtonClickListener {
                    deadline = SimpleDateFormat(
                        "dd MMM yy",
                        Locale("RUS")
                    ).format(Timestamp(datePicker.selection!!))
                    binding.deadlineTv.text = deadline
                }
            } else {
                deadline = ""
                binding.deadlineTv.visibility = View.INVISIBLE
                binding.deadlineTv.text = ""
            }
        }

        binding.deadlineTv.setOnClickListener {
            val fragmentManager: FragmentManager by lazy {
                (requireActivity() as AppCompatActivity).supportFragmentManager
            }
            datePicker.show(fragmentManager, "")
            datePicker.addOnPositiveButtonClickListener {
                deadline = SimpleDateFormat(
                    "dd MMM yy",
                    Locale("RUS")
                ).format(Timestamp(datePicker.selection!!))
                binding.deadlineTv.text = deadline
            }
        }
    }

    private fun bindSettings() {
        with(binding) {
            nameTv.setText(toDoItem!!.name)
            descriptionTv.setText(toDoItem!!.description)
            importanceChoosingView.setImportance(toDoItem!!.importance)
            deadlineTv.text = toDoItem!!.deadLine
        }
    }

    private fun createNewItem(): ToDoItem {
        return ToDoItem(
            binding.nameTv.text.toString(),
            binding.descriptionTv.text.toString(),
            binding.importanceChoosingView.getImportance(),
            deadline
        )
    }

    private fun updateTempItem(): ToDoItem {
        with(toDoItem!!) {
            name = binding.nameTv.text.toString()
            description = binding.descriptionTv.text.toString()
            importance = binding.importanceChoosingView.getImportance()
        }
        return toDoItem!!
    }*/
}