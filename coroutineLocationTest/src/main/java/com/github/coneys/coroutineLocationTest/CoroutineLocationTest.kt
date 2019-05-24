package com.github.coneys.coroutineLocationTest

import com.github.coneys.coroutineLocation.CoroutineLocationSettings


object CoroutineLocationTest {
    internal val testProvider by lazy { TestProvider() }

    fun startTestMode(): TestProvider {

        CoroutineLocationSettings.strategy = TestStrategy(testProvider)
        return testProvider
    }
}