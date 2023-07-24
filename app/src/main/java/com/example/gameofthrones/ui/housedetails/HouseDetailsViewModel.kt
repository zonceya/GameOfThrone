package com.example.gameofthrones.ui.housedetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.gameofthrones.data.GameOfThronesResponsesItem
import com.example.gameofthrones.data.entities.HouseGameOfThrones
import com.example.gameofthrones.data.repository.HouseRepository
import com.example.gameofthrones.util.Resource

class HouseDetailsViewModel  @ViewModelInject constructor(
    private val repository: HouseRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _house = _id.switchMap { id ->
        repository.getHouse(id)
    }
    val houses: LiveData<Resource<HouseGameOfThrones>> = _house


    fun start(id: Int) {
        _id.value = id
    }

}