package com.kunila.ocean.domain.usecase

import com.kunila.ocean.data.repository.CsvRepository
import com.kunila.ocean.domain.model.AirQualityData

class GetPollutionDataUseCase (private val csvRepository: CsvRepository) {

    operator fun invoke(filename: String): List<AirQualityData> {
        return csvRepository.loadAirQualityData(filename)
    }
}