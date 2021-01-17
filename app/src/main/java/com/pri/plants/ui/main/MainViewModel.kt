package com.pri.plants.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.pri.plants.data.Plant
import com.pri.plants.repository.PlantsRepository

class MainViewModel @ViewModelInject constructor(private val plantsRepository: PlantsRepository): ViewModel() {
    fun getPlantList() = plantsRepository.loadPlantList()
}