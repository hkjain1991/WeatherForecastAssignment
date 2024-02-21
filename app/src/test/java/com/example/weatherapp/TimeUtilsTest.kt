package com.example.weatherapp

import com.example.weatherapp.utils.TimeUtil
import org.junit.Test
import org.junit.Assert.assertEquals

/**
 * Test for [TimeUtil]
 */
class TimeUtilsTest {
    @Test
    fun testToGetExtractTimeFromString() {
        val date = TimeUtil.extractTimeFromString("2024-02-20 09:00:00")
        assertEquals("09:00", date)
    }

    @Test
    fun testToGetExtractDateFromString() {
        val date = TimeUtil.extractDateFromString("2024-02-20 09:00:00")
        assertEquals("20-02-2024", date)
    }
}