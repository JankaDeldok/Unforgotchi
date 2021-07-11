package com.jlp.unforgotchi.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel(application: Application): AndroidViewModel(application) {

    val readAllLocations: LiveData<List<Location>>

    private val repository: LocationsRepository

    val getAllWifis : LiveData<List<String?>>

    init {
        val locationsDao = LocationsDatabase.getDatabase(application).locationsDao()
        repository = LocationsRepository(locationsDao)

        readAllLocations = repository.readAllLocations

        getAllWifis = repository.getAllWifis
    }

    fun addLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLocation(location)
        }
    }

    fun updateLocation(location: Location,wifiName : String? = null){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLocation(location,wifiName)
        }
    }

    fun updateLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLocation(location)
        }
    }

    fun deleteLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLocation(location)
        }
    }
}