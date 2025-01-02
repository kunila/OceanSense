package com.kunila.ocean.data.repository

import com.kunila.ocean.data.datasource.CsvDataSource
import com.kunila.ocean.domain.model.AirQualityData
import com.kunila.ocean.domain.model.TemperatureData
import com.kunila.ocean.domain.model.WindSpeedData

class CsvRepository(private val csvDataSource: CsvDataSource)  {

    fun loadAirQualityData(filename: String): List<AirQualityData> {
        return csvDataSource.loadPollutionData(filename)
    }

    fun loadWindSpeedData(filename: String): List<WindSpeedData> {
        return csvDataSource.loadWindCsvData(filename)
    }

    fun loadTemperatureData(filename: String): List<TemperatureData> {
        return csvDataSource.loadTemperatureData(filename)
    }
}