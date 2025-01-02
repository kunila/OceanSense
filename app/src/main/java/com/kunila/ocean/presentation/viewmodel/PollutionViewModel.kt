package com.kunila.ocean.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunila.ocean.domain.model.AirQualityData
import com.kunila.ocean.domain.usecase.GetPollutionDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PollutionViewModel @Inject constructor(
    private val getPollutionDataUseCase: GetPollutionDataUseCase,
):ViewModel() {

    private val _data = mutableStateOf<List<AirQualityData>>(emptyList())
    val data: State<List<AirQualityData>> get() = _data

    init {
        loadPollutionData()
    }

    private fun loadPollutionData()
    {
        viewModelScope.launch {
            _data.value = getPollutionDataUseCase("pollution.csv")
        }
    }
}