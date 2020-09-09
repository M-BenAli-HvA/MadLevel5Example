package com.example.madlevel3example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_reminder.*
import kotlinx.android.synthetic.main.fragment_add_reminder.view.*

const val REQ_REMINDER_KEY = "req_reminder"
const val BUNDLE_REMINDER_KEY = "bundle_reminder"

class AddReminderFragment : Fragment() {

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
            setFragmentResult(REQ_REMINDER_KEY,
            bundleOf(Pair(BUNDLE_REMINDER_KEY, reminderText)))
            findNavController().popBackStack()
        } else {
            Toast.makeText(activity, R.string.not_valid_reminder, Toast.LENGTH_SHORT).show()
        }

    }
}