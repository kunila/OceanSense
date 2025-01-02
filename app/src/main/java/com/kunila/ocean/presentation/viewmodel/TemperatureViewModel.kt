package com.kunila.ocean.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunila.ocean.domain.model.TemperatureData
import com.kunila.ocean.domain.usecase.GetTemperatureDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemperatureViewModel @Inject constructor(
    private val getTemperatureDataUseCase: GetTemperatureDataUseCase,
):ViewModel() {

    private val _data = mutableStateOf<List<TemperatureData>>(emptyList())
    val data: State<List<TemperatureData>> get() = _data

    init {
        loadTemperatureData()
    }

    private fun loadTemperatureData() {
        viewModelScope.launch {
            _data.value = getTemperatureDataUseCase("temperature.csv")
        }
    }
}
