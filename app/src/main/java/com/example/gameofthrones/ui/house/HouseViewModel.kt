package com.example.gameofthrones.ui.house

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.gameofthrones.data.repository.HouseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HouseViewModel  @ViewModelInject constructor(
    private val repository: HouseRepository
) : ViewModel() {

    val houses = repository.getHouses()
}