package com.kunila.ocean.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunila.ocean.domain.model.AirQualityData
import com.kunila.ocean.domain.model.TemperatureData
import com.kunila.ocean.domain.usecase.GetTemperatureDataUseCase
import com.kunila.ocean.presentation.viewmodel.PollutionViewModel.Companion.filterAirQualityData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemperatureViewModel @Inject constructor(
    private val getTemperatureDataUseCase: GetTemperatureDataUseCase,
):ViewModel() {

    private val _data = mutableStateOf<List<TemperatureData>>(emptyList())
    val data: State<List<TemperatureData>> get() = _data

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _temperatureData = derivedStateOf {
        filterTemperatureData(_data.value, _searchQuery.value)
    }
    val temperatureData: State<List<TemperatureData>> get() = _temperatureData

    init {
        loadTemperatureData()
    }

    private fun loadTemperatureData() {
        viewModelScope.launch {
            _data.value = getTemperatureDataUseCase("temperature.csv")
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    companion object {
        fun filterTemperatureData(data: List<TemperatureData>, query: String): List<TemperatureData> {
            return if (query.isEmpty()) {
                data
            } else {
                data.filter { item ->
                    item.region.contains(query, ignoreCase = true) ||
                            item.date.contains(query, ignoreCase = true)
                }
            }
        }
    }
}
