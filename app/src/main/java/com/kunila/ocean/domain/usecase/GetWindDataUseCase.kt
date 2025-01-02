package com.kunila.ocean.domain.usecase

import com.kunila.ocean.data.repository.CsvRepository
import com.kunila.ocean.domain.model.WindSpeedData

class GetWindDataUseCase(private val csvRepository: CsvRepository) {

    operator fun invoke(filename: String): List<WindSpeedData> {
        return csvRepository.loadWindSpeedData(filename)
    }
}