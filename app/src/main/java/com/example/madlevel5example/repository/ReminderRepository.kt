package com.example.madlevel5example.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5example.models.Reminder
import com.example.madlevel5example.dao.ReminderDao
import com.example.madlevel5example.database.ReminderRoomDatabase

class ReminderRepository(context: Context) {

    private var reminderDao: ReminderDao

    init {
        val reminderRoomDatabase =
            ReminderRoomDatabase.getDatabase(context)
        reminderDao = reminderRoomDatabase!!.reminderDao()
    }

    fun getAllReminders(): LiveData<List<Reminder>> {
        return reminderDao?.getAllReminders() ?:
                MutableLiveData(emptyList())
    }

    suspend fun insertReminder(reminder: Reminder) {
        return reminderDao.insertReminder(reminder)
    }

    suspend fun deleteReminder(reminder: Reminder) {
        return reminderDao.deleteReminder(reminder)
    }

}