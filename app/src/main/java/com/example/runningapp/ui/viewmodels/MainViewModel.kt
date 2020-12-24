package com.example.runningapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningapp.db.RunObject
import com.example.runningapp.repo.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    val sortedListByDate = mainRepository.getAllRunSortedByDate()

    fun insertRun(run: RunObject) = viewModelScope.launch{
        mainRepository.insertRun(run)
    }

}