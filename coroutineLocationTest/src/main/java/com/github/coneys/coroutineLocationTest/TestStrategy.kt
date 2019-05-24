package com.github.coneys.coroutineLocationTest

import com.github.coneys.coroutineLocation.LocationStrategy
import com.github.coneys.coroutineLocation.state.LocationStateListener
import com.github.coneys.coroutineLocationTest.TestProvider
import com.github.coneys.coroutinePermission.staticPermission.SuspendPermissions
import com.google.android.gms.location.LocationRequest

internal class TestStrategy(val testProvider: TestProvider) : LocationStrategy {

    override fun isLocationEnabled() = testProvider.isLocationEnabled

    override suspend fun getLastUserLocation() = testProvider.lastUserLocation


    override suspend fun startListening(
        coroutinePermissions: SuspendPermissions?,
        locationRequest: LocationRequest,
        locationListener: LocationStateListener
    ) {
        testProvider.registerRequest(locationRequest, locationListener)
    }

}