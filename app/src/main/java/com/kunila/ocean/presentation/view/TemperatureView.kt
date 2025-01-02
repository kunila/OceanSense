package com.kunila.ocean.presentation.view

import android.view.Display.Mode
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ocean.R
import com.kunila.ocean.data.datasource.CsvDataSource
import com.kunila.ocean.data.repository.CsvRepository
import com.kunila.ocean.domain.model.TemperatureData
import com.kunila.ocean.domain.usecase.GetTemperatureDataUseCase
import com.kunila.ocean.presentation.viewmodel.TemperatureViewModel

@Composable
fun TemperatureScreen(viewModel: TemperatureViewModel) {
    val data by viewModel.data
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(data) { item ->
                TemperatureRow(item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TemperatureRow(item: TemperatureData) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Column(
            modifier = Modifier.padding(16.dp)
        )
        {
            Text(text = "Region: ${item.region}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(text = "Temperature: ${item.temperature}")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Date: ${item.date}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TemperatureScreenPreview() {
    val csvRepository = CsvRepository(CsvDataSource(LocalContext.current))
    val viewModel = TemperatureViewModel(
        GetTemperatureDataUseCase(csvRepository))
    TemperatureScreen(viewModel = viewModel)
}