package com.jlp.unforgotchi.db

import androidx.lifecycle.LiveData


class LocationsRepository(private val locationsDao: LocationsDao) {

    val readAllLocations: LiveData<List<Location>> = locationsDao.readAllLocations()

    val getAllWifis : LiveData<List<String?>> = locationsDao.getAllWifis()

    suspend fun addLocation(location: Location){
        locationsDao.addLocation(location)
    }

    suspend fun getLocationById(id : Int) : Location{
        return locationsDao.getLocationById(id)
    }

    suspend fun updateLocation(location: Location){
        locationsDao.updateLocation(location)
    }

    suspend fun updateLocation(location: Location, wifiName : String?){
        val replacementLocation = Location(location.location_id, location.text,location.image,wifiName,location.listId)
        locationsDao.updateLocation(replacementLocation)
    }

    suspend fun deleteLocation(location: Location){
        locationsDao.deleteLocation(location)
    }

    suspend fun getLocations(): List<Location> {
        return locationsDao.getLocations()
    }

    suspend fun removeListId(id : Int) : Boolean {
        locationsDao.removeListId(id)
        return true
    }

    suspend fun removeWifi(wifi : String) : Boolean {
        locationsDao.removeWifi(wifi)
        return true
    }

    //This SHOULD work, but somehow room changes the value always to 0 for no reason at all
    //This function is deprecated so to speak. It's still here because in the future we
    //may fix it
    //fun setLatestLocation(location: Location){
    //    locationsDao.setLatestLocation(location.location_id)
    //}
}