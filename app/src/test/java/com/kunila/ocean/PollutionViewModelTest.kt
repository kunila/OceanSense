package com.kunila.ocean

import com.kunila.ocean.presentation.viewmodel.PollutionViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import com.kunila.ocean.domain.model.AirQualityData


class PollutionViewModelTest {

    @Test
    fun `filterAirQualityData returns all items when query is empty`() {
        // Arrange
        val testData = listOf(
            AirQualityData(region = "New York", date = "2024-01-01"),
            AirQualityData(region = "London", date = "2024-01-02")
        )

        // Act
        val result = PollutionViewModel.filterAirQualityData(testData, "")

        // Assert
        assertEquals(testData, result)
    }

    @Test
    fun `filterAirQualityData filters by region correctly`() {
        // Arrange
        val testData = listOf(
            AirQualityData(region = "New York", date = "2024-01-01"),
            AirQualityData(region = "London", date = "2024-01-02")
        )

        // Act
        val result = PollutionViewModel.filterAirQualityData(testData, "London")

        // Assert
        assertEquals(1, result.size)
        assertEquals("London", result[0].region)
    }

}