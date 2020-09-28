package com.example.madlevel5example.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2example.ReminderAdapter
import com.example.madlevel5example.R
import com.example.madlevel5example.models.Reminder
import com.example.madlevel5example.repository.ReminderRepository
import com.example.madlevel5example.viewmodels.ReminderViewModel
import kotlinx.android.synthetic.main.fragment_reminders.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RemindersFragment : Fragment() {

//    private lateinit var reminderRoomRepository: ReminderRepository

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)
    private val viewModel: ReminderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeAddReminderResult()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvReminders.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvReminders.adapter = reminderAdapter
        rvReminders.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(rvReminders)
    }


    //    Observe the data from the viewModel when the reminders list changes
    private fun observeAddReminderResult() {
                viewModel.reminders.observe(viewLifecycleOwner, Observer {
                    reminders ->
                    this@RemindersFragment.reminders.clear()
                    this@RemindersFragment.reminders.addAll(reminders)
                    reminderAdapter.notifyDataSetChanged()
                })
    }

//    Observes a result from the fragment listener
/*    private fun observeAddReminderResult() {
            setFragmentResultListener(REQ_REMINDER_KEY) { key, bundle ->
                bundle.getString(BUNDLE_REMINDER_KEY)?.let {
                    val reminder = Reminder(it)

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            reminderRepository.insertReminder(reminder)
                        }
                        getRemindersFromDatabase()
                    }
                } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!")

            }
        }


    private fun getRemindersFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val reminders = withContext(Dispatchers.IO) {
                reminderRepository.getAllReminders()
            }
            this@MainActivity.reminders.clear()
            this@MainActivity.reminders.addAll(reminders)
            reminderAdapter.notifyDataSetChanged()
        }
    }*/


        private fun createItemTouchHelper(): ItemTouchHelper {
        var callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val reminderToDelete = reminders[position]
                viewModel.deleteReminder(reminderToDelete)
            }
        }

        return ItemTouchHelper(callback)
    }

//    private fun getRemindersFromDatabase() {
//        CoroutineScope(
//            Dispatchers.Main
//        ).launch {
//            val reminders =
//                withContext(Dispatchers.IO) {
//                    reminderRoomRepository.getAllReminders()
//                }
//            this@RemindersFragment.reminders.clear()
//            this@RemindersFragment.reminders.addAll(reminders)
//            reminderAdapter.notifyDataSetChanged()
//        }
//    }
}