package com.kunila.ocean.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunila.ocean.domain.model.WindSpeedData
import com.kunila.ocean.domain.usecase.GetWindDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WindViewModel @Inject constructor(
    private val getWindDataUseCase: GetWindDataUseCase,
):ViewModel() {

    private val _data = mutableStateOf<List<WindSpeedData>>(emptyList())
    val data: State<List<WindSpeedData>> get() = _data

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _windData = derivedStateOf {
        filterWindData(_data.value, _searchQuery.value)
    }
    val windData: State<List<WindSpeedData>> get() = _windData

    init {
        loadWindData()
    }

    private fun loadWindData()
    {
        viewModelScope.launch {
            _data.value = getWindDataUseCase("wind.csv")
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    companion object {
        fun filterWindData(data: List<WindSpeedData>, query: String): List<WindSpeedData> {
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