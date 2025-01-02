package com.kunila.ocean.data.datasource

import android.content.Context
import com.kunila.ocean.domain.model.AirQualityData
import com.kunila.ocean.domain.model.TemperatureData
import com.kunila.ocean.domain.model.WindSpeedData

class CsvDataSource(private val context: Context) {

    fun loadPollutionData(filename: String): List<AirQualityData> {
        val inputStream = context.assets.open(filename)
        val reader = inputStream.bufferedReader()
        val rows = mutableListOf<AirQualityData>()
        reader.useLines { lines ->
            lines.drop(1).forEach { line -> // Skip header
                val rowData = line.split(",")
                rows.add(
                    AirQualityData(
                        region = rowData[0],
                        co2 = rowData[1].toDouble(),
                        no2 = rowData[2].toDouble(),
                        so2 = rowData[3].toDouble(),
                        pm25 = rowData[4].toDouble(),
                        o3 = rowData[5].toDouble(),
                        nox = rowData[6].toDouble(),
                        date = rowData[7]
                    )
                )
            }
        }
        return rows
    }

    fun loadWindCsvData(filename: String): List<WindSpeedData>  {
        val inputStream = context.assets.open(filename)
        val reader = inputStream.bufferedReader()
        val rows = mutableListOf<WindSpeedData>()
        reader.useLines { lines ->
            lines.drop(1).forEach { line -> // Skip header
                val rowData = line.split(",")
                rows.add(
                    WindSpeedData(
                        region = rowData[0],
                        speed = rowData[1].toDouble(),
                        date = rowData[2]
                    )
                )
            }
        }
        return rows
    }

    fun loadTemperatureData(filename: String): List<TemperatureData>  {
        val inputStream = context.assets.open(filename)
        val reader = inputStream.bufferedReader()
        val rows = mutableListOf<TemperatureData>()
        reader.useLines { lines ->
            lines.drop(1).forEach { line -> // Skip header
                val rowData = line.split(",")
                rows.add(
                    TemperatureData(
                        region = rowData[0],
                        temperature = rowData[1].toDouble(),
                        date = rowData[2]
                    )
                )
            }
        }
        return rows
    }
}