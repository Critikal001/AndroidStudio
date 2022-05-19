package com.example.rentmycar

import androidx.test.ext.junit.runners.AndroidJUnit4

import com.example.rentmycar.data.api.ServiceProvider
import com.example.rentmycar.data.model.api.post.Car
import com.example.rentmycar.data.model.api.post.EngineType
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CarServiceTest {
    private val carService = Mockito.mock(ServiceProvider::class.java)

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Test
    @Throws(Exception::class)
    fun carServiceTest() {
        testScope.runBlockingTest {
            val car = createCarResponse()
            Mockito.`when`(carService.getCarById(1)).thenReturn(Response.success(car))

            val carToTest = carService.getCarById(1)
            assertEquals(carToTest.body()?.registrationNr, car.registrationNr)
        }
    }

    private fun createCarResponse(): Car {
        return Car(
            1,
            2018,
            1200,
            "TDI 1.5",
            110.0,
            EngineType.ICEEngine,
            "Benzine",
            2.0,
            2.2)
    }
}