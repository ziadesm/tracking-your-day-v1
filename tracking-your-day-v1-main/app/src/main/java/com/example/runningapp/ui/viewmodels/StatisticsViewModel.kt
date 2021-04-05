package com.example.runningapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningapp.repo.MainRepository
import javax.inject.Inject

class StatisticsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {



}