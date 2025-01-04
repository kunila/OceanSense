package com.kunila.ocean.domain.model

data class AirQualityData(
    val region: String,
    val co2: Double? = null,
    val no2: Double? = null,
    val so2: Double? = null,
    val pm25: Double? = null,
    val o3: Double? = null,
    val nox: Double? = null,
    val date: String
)
