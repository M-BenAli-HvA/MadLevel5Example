package com.example.madlevel5example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5example.R
import com.example.madlevel5example.models.Reminder
import com.example.madlevel5example.viewmodels.ReminderViewModel
import kotlinx.android.synthetic.main.fragment_add_reminder.*
import kotlinx.android.synthetic.main.fragment_add_reminder.view.*



class AddReminderFragment : Fragment() {

    val viewModel: ReminderViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnAddReminder.setOnClickListener {
            onAddReminder()
        }
    }

    private fun onAddReminder() {
        val reminderText = etReminderName.text.toString()

        if(reminderText.isNotBlank()){
            val reminder: Reminder = Reminder(reminderText)
            viewModel.insertReminder(reminder)
            //"pop" the backstack,
            // this means we destroy this fragment and go back to the RemindersFragment
            findNavController().popBackStack()
        } else {
            Toast.makeText(activity,
                R.string.not_valid_reminder, Toast.LENGTH_SHORT).show()
        }

    }
}