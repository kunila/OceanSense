package com.kunila.ocean.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ocean.R
import com.kunila.ocean.data.datasource.CsvDataSource
import com.kunila.ocean.data.repository.CsvRepository
import com.kunila.ocean.domain.model.AirQualityData
import com.kunila.ocean.domain.usecase.GetPollutionDataUseCase
import com.kunila.ocean.presentation.viewmodel.PollutionViewModel

@Composable
fun PollutionScreen(viewModel: PollutionViewModel) {
    val data by viewModel.data
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(data) { item ->
                AirQualityRow(item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AirQualityRow(item: AirQualityData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "Region: ${item.region}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DataItem("CO2", item.co2.toString())
                DataItem("NO2", item.no2.toString())
                DataItem("SO2", item.so2.toString())
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DataItem("PM25", item.pm25.toString())
                DataItem("O3", item.o3.toString())
                DataItem("NOX", item.nox.toString())
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Date: ${item.date}", style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun DataItem(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.titleSmall)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun PollutionScreenPreview() {

    val csvRepository = CsvRepository(CsvDataSource(LocalContext.current))
    val viewModel = PollutionViewModel(GetPollutionDataUseCase(csvRepository))
    PollutionScreen(viewModel = viewModel)
}