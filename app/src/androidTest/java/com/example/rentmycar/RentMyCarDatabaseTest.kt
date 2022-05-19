package com.example.rentmycar

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.rentmycar.data.model.api.post.Car
import com.example.rentmycar.data.model.api.post.EngineType
import com.example.rentmycar.data.room.*
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RentMyCarDatabaseTest {

    private lateinit var carDao: CarDao
    private lateinit var locationDao: LocationDao
    private lateinit var db: RentMyCarDatabase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(appContext, RentMyCarDatabase::class.java)
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()

        carDao = db.carDao()
        locationDao = db.locationDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertUpdateDeleteAndGetCarTest() {
        testScope.runBlockingTest {
            val car = CarRoom(
                1,
                2018,
                1200,
                "TDI 1.5",
                110.0,
                EngineType.ICEEngine,
                "Benzine",
                2.0,
                2.2,

            )
            carDao.createCar(car)
            val getCar = carDao.getCar(1)
            assertNotNull(getCar)
            assertEquals(getCar.model, "TDI 1.5")


            carDao.deleteCar(1)
            val getDeletedCar = carDao.getCar(1)
            assertNull(getDeletedCar)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertDeleteAndGetLocationTest() {
        testScope.runBlockingTest {
            val location = LocationRoom(
                1,
                "Veemarktstraat",
                "66",
                "4811ZJ",
                "Breda",
                "Netherlands",
                51.588896000000005,
                4.7796635
            )
            locationDao.createLocation(location)
            val getLocation = locationDao.getLocation(1)
            assertNotNull(getLocation)
            assertEquals(getLocation.street, "Veemarktstraat")

            locationDao.deleteLocation(1)
            val getDeletedLocation = locationDao.getLocation(1)
            assertNull(getDeletedLocation)
        }
    }
 }
