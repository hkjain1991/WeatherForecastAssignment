package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.model.response.WeatherListData
import com.example.weatherapp.repository.RemoteRepository
import com.example.weatherapp.viewmodel.WeatherDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Test for [WeatherDetailsViewModel]
 */
class WeatherDetailsViewModelTest {

    private lateinit var viewModel: WeatherDetailsViewModel
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = WeatherDetailsViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test to verify tht weather data has got success`() = runTest {
        val mock = mockk<RemoteRepository>()
        coEvery {
            mock.getUpcomingWeatherData("305901")
        } returns WeatherListData(emptyList())
        viewModel.callUpcomingWeatherDetails("305901", mock, dispatcher)

        assertEquals(viewModel.weatherLiveData.value?.isEmpty(), true)
    }

    @Test
    fun `test to verify tht weather data has null body`() = runTest {
        val mock = mockk<RemoteRepository>()
        coEvery {
            mock.getUpcomingWeatherData("305901")
        } returns null
        viewModel.callUpcomingWeatherDetails("305901", mock, dispatcher)

        assertNull(viewModel.weatherLiveData.value)
    }
}