package com.example.runningapp.repo
import com.example.runningapp.db.RunDao
import com.example.runningapp.db.RunObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val runDao: RunDao) {

    suspend fun insertRun(run: RunObject) = runDao.insertRun(run)

    suspend fun deleteRun(run: RunObject) = runDao.deleteRun(run)

    fun getAllRunSortedByDate() = runDao.getAllRunsSortedByDate()

    fun getAllRunSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMilies()

    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()

    fun getAllRunsSortedByAverageSpeed() = runDao.getAllRunsSortedByAverageSpeed()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()

    fun getTotalAverageSpeed() = runDao.getTotalAverageSpeed()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalDistanceInMeters() = runDao.getTotalDistanceInMeters()

    fun getTotalTime() = runDao.getTotalTime()
}