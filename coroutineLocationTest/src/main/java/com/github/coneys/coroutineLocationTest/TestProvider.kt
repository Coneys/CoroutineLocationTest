package com.github.coneys.coroutineLocationTest

import com.github.coneys.coroutineLocation.state.LocationState
import com.github.coneys.coroutineLocation.state.LocationStateListener
import com.google.android.gms.location.LocationRequest
import java.util.*

class TestProvider internal constructor() {

    private val map = HashMap<LocationRequest, LocationStateListener>()

    var lastUserLocation: LocationState = LocationState.NoLocation

    var isLocationEnabled = true

    fun registerRequest(locationRequest: LocationRequest, locationListener: LocationStateListener) {
        map[locationRequest] = locationListener
    }

    fun pushStateForRequest(locationRequest: LocationRequest, state: LocationState) {
        lastUserLocation = state
        map.filter { it.key == locationRequest }.values.forEach {
            it.onNewState(state)
        }
    }

    fun pushStateForAll(state: LocationState) {
        lastUserLocation = state
        map.values.forEach {
            it.onNewState(state)
        }
    }
}