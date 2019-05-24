package com.github.coneys.coroutineLocation

import androidx.lifecycle.ViewModel
import com.github.coneys.coroutineLocation.provider.getLastLocation
import com.github.coneys.coroutineLocation.provider.observeLocation
import com.github.coneys.coroutineLocation.state.LocationState
import com.github.coneys.coroutineLocationTest.CoroutineLocationTest
import com.github.coneys.coroutinePermissionTest.TestCoroutinePermission
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.coroutines.CoroutineContext

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val testProvider = CoroutineLocationTest.startTestMode()

        val viewModel = DummyViewModel()
        viewModel.observeLocationState()

        testProvider.pushStateForAll(LocationState.LocationDisabled)

        runBlocking {
            println("Last state ${viewModel.getLastState()}")
        }

        testProvider.pushStateForAll(LocationState.NoLocation)

        runBlocking {
            println("Last state ${viewModel.getLastState()}")
        }

    }
}

class DummyViewModel() : ViewModel(), CoroutineScope {
    override var coroutineContext: CoroutineContext = Dispatchers.Unconfined

    suspend fun getLastState(): LocationState {
        return getLastLocation()
    }

    fun observeLocationState() {

        val request = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(6000)
            .setFastestInterval(1000)

        val permissions = TestCoroutinePermission(true)


        launch {
            observeLocation(request, permissions) {
                println("New location state $it")
            }
        }
    }

}