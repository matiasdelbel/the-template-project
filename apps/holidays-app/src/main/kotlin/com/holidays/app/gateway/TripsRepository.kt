package com.holidays.app.gateway

import com.common.data.di.IODispatcher
import com.holidays.app.gateway.database.TripDao
import com.holidays.app.gateway.database.TripLinksDao
import com.holidays.app.gateway.dto.TripDto
import com.holidays.app.gateway.dto.toTripDto
import com.holidays.app.model.Trip
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripsRepository @Inject constructor(
    private val tripsDao: TripDao,
    private val tripLinksDao: TripLinksDao,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) {

    val trips = tripsDao
        .loadAll()
        .map { it.map { dto -> dto.toTrip() } }

    suspend fun findById(tripId: Long): Trip = withContext(dispatcher) {
        val tripDto = async { tripsDao.findById(tripId).toTrip() }
        val linksDto = async { tripLinksDao.findById(tripId) }

        Trip(
            id = tripId,
            name = tripDto.await().name,
            links = linksDto.await().map { it.toTripLink() }
        )
    }

    suspend fun delete(trip: Trip) = tripsDao
        .delete(trip = trip.toTripDto())

    suspend fun insertTrip(name: String) = tripsDao
        .insert(trip = TripDto(name))

    suspend fun update(trip: Trip) = tripsDao
        .update(trip = trip.toTripDto())
}
