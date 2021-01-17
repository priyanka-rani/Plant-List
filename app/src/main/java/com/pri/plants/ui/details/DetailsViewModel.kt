package com.pri.plants.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.pri.plants.repository.PlantsRepository

class DetailsViewModel @ViewModelInject constructor(
    private val plantsRepository: PlantsRepository
) : ViewModel() {
    val plantId = MutableLiveData<Int>()
    val plant = plantId.switchMap {
        plantsRepository.getPlant(it)
    }
}