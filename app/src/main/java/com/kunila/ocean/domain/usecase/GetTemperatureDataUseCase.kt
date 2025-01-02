package com.kunila.ocean.domain.usecase

import com.kunila.ocean.data.repository.CsvRepository
import com.kunila.ocean.domain.model.TemperatureData

class GetTemperatureDataUseCase(private val csvRepository: CsvRepository) {

    operator fun invoke(filename: String): List<TemperatureData> {
        return csvRepository.loadTemperatureData(filename)
    }
}