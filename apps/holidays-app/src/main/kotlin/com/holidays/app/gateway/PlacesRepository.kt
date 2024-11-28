package com.holidays.app.gateway

import com.holidays.app.gateway.database.PlacesDao
import com.holidays.app.gateway.database.dto.PlaceDto
import com.holidays.app.gateway.database.dto.toCoordinateDto
import com.holidays.app.gateway.database.dto.toPlaceDto
import com.holidays.app.model.Coordinate
import com.holidays.app.model.Place
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesRepository @Inject constructor(
    private val placesDao: PlacesDao
) {

    val places = placesDao
        .loadAll()
        .map { it.map { dto -> dto.toPlace() } }

    suspend fun findById(placeId: Long): Place = placesDao
        .findById(placeId)
        .toPlace()

    suspend fun delete(place: Place) = placesDao
        .delete(place = place.toPlaceDto())

    suspend fun insertPlace(name: String, coordinate: Coordinate) = placesDao
        .insert(place = PlaceDto(name, coordinate.toCoordinateDto()))

    suspend fun update(place: Place) = placesDao
        .update(place = place.toPlaceDto())
}
