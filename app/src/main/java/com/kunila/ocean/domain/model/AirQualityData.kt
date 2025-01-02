package com.kunila.ocean.domain.model

data class AirQualityData(
    val region: String,
    val co2: Double,
    val no2: Double,
    val so2: Double,
    val pm25: Double,
    val o3: Double,
    val nox: Double,
    val date: String
)
