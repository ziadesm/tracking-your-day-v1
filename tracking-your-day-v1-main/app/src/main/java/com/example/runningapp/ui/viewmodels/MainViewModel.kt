package com.example.runningapp.ui.viewmodels
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningapp.db.RunObject
import com.example.runningapp.repo.MainRepository
import com.example.runningapp.utilities.SortType
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val sortedListByDate = mainRepository.getAllRunSortedByDate()
    private val sortedListByDistance = mainRepository.getAllRunsSortedByDistance()
    private val sortedListByBurnedCalories = mainRepository.getAllRunsSortedByCaloriesBurned()
    private val sortedListByAvgSpeed = mainRepository.getAllRunsSortedByAverageSpeed()
    private val sortedListByTimeInMillis = mainRepository.getAllRunSortedByTimeInMillis()

    val runs = MediatorLiveData<List<RunObject>>()
    var sortType = SortType.DATE

    init {
        runs.addSource(sortedListByDate){ result ->
            if (sortType == SortType.DATE) {
                result?.let {
                    runs.value = it
                }
            }
        }
        runs.addSource(sortedListByBurnedCalories){ result ->
            if (sortType == SortType.CALORIES_BURNED) {
                result?.let {
                    runs.value = it
                }
            }
        }
        runs.addSource(sortedListByTimeInMillis){ result ->
            if (sortType == SortType.RUNNING_TIME) {
                result?.let {
                    runs.value = it
                }
            }
        }
        runs.addSource(sortedListByDistance){ result ->
            if (sortType == SortType.DISTANCE) {
                result?.let {
                    runs.value = it
                }
            }
        }
        runs.addSource(sortedListByAvgSpeed){ result ->
            if (sortType == SortType.AVG_SPEED) {
                result?.let {
                    runs.value = it
                }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType){
        SortType.DATE -> sortedListByDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> sortedListByTimeInMillis.value?.let { runs.value = it }
        SortType.DISTANCE -> sortedListByDistance.value?.let { runs.value = it }
        SortType.AVG_SPEED -> sortedListByAvgSpeed.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> sortedListByBurnedCalories.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }
    fun insertRun(run: RunObject) = viewModelScope.launch{
        mainRepository.insertRun(run)
    }

}