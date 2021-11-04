package com.example.allinone.page2.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.allinone.page2.room.database.schedule.Schedule
import com.example.allinone.page2.room.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class ScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    fun roomSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    fun subRoomSchedule(name: String): Flow<List<Schedule>> = scheduleDao.getByStopName(name)
}

class ScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}