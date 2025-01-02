package com.kunila.ocean.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kunila.ocean.domain.usecase.GetPollutionDataUseCase
import com.kunila.ocean.domain.usecase.GetTemperatureDataUseCase
import com.kunila.ocean.domain.usecase.GetWindDataUseCase

class ViewModelFactory (private val getPollutionDataUseCase: GetPollutionDataUseCase,
                        private val getWindSpeedDataUseCase: GetWindDataUseCase,
                        private val getTemperatureDataUseCase: GetTemperatureDataUseCase,
                        private val fileName: String,
                        private val viewModelType: Class<out ViewModel>
):ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(PollutionViewModel::class.java) -> {
                    PollutionViewModel(getPollutionDataUseCase) as T
                }
                modelClass.isAssignableFrom(WindViewModel::class.java) -> {
                    WindViewModel(getWindSpeedDataUseCase) as T
                }
                modelClass.isAssignableFrom(TemperatureViewModel::class.java) -> {
                    TemperatureViewModel(getTemperatureDataUseCase) as T
                }
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            }

        }
}