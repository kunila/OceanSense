package com.kunila.ocean.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
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

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _pollutionData = derivedStateOf {
        filterAirQualityData(_data.value, _searchQuery.value)
    }
    val pollutionData: State<List<AirQualityData>> get() = _pollutionData


    init {
        loadPollutionData()
    }

    private fun loadPollutionData()
    {
        viewModelScope.launch {
            _data.value = getPollutionDataUseCase("pollution.csv")
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    companion object {
        fun filterAirQualityData(data: List<AirQualityData>, query: String): List<AirQualityData> {
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