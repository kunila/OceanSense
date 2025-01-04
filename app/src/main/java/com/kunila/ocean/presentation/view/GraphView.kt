package com.kunila.ocean.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kunila.ocean.R
import com.kunila.ocean.presentation.viewmodel.GraphViewModel

@Composable
fun GraphScreen(viewModel: GraphViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            AssetImage(assetName = "images/aquasense.png", modifier = Modifier.height(200.dp).fillMaxWidth())
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GraphScreenPreview() {
    GraphScreen(GraphViewModel())
}