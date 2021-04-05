package com.example.runningapp.db
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: RunObject)

    @Delete
    suspend fun deleteRun(run: RunObject)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<RunObject>>

    @Query("SELECT * FROM running_table ORDER BY timeInMilies DESC")
    fun getAllRunsSortedByTimeInMilies(): LiveData<List<RunObject>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<RunObject>>

    @Query("SELECT * FROM running_table ORDER BY avergeSpeed DESC")
    fun getAllRunsSortedByAverageSpeed(): LiveData<List<RunObject>>

    @Query("SELECT * FROM running_table ORDER BY distance DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<RunObject>>

    @Query("SELECT SUM(timeInMilies) FROM running_table")
    fun getTotalTime(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned(): LiveData<Long>

    @Query("SELECT SUM(distance) FROM running_table")
    fun getTotalDistanceInMeters(): LiveData<Int>

    @Query("SELECT AVG(avergeSpeed) FROM running_table")
    fun getTotalAverageSpeed(): LiveData<Float>
}