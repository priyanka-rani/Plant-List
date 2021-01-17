package com.pri.plantae.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.pri.plantae.repository.PlantsRepository

class MainViewModel @ViewModelInject constructor(private val plantsRepository: PlantsRepository): ViewModel() {
    fun getPlantList() = plantsRepository.loadPlantList()
}