package com.kunila.ocean.presentation.view

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kunila.ocean.R
import com.kunila.ocean.data.datasource.ProductDataSource
import com.kunila.ocean.data.repository.ProductRepository
import com.kunila.ocean.domain.model.ProductData
import com.kunila.ocean.domain.usecase.GetProductDataUseCase
import com.kunila.ocean.presentation.events.UIEvent
import com.kunila.ocean.presentation.navigation.Screens
import com.kunila.ocean.presentation.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(viewModel: ProductsViewModel,navController: NavController) {
    val data by viewModel.data
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UIEvent.OrderProduct -> {
                    navController.currentBackStackEntry?.savedStateHandle?.set("product", event.product)
                    navController.navigate(Screens.ContactScreen.route)
                    // Handle order product event
                    //Toast.makeText(context, "${event.product.name} ordered!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        LazyColumn {
            items(data) { item ->
                ProductItem(product = item, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun ProductItem(product: ProductData, viewModel: ProductsViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AssetImage(assetName = "${product.image}", modifier = Modifier.height(180.dp).fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, style = MaterialTheme.typography.headlineSmall)
            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "$${product.price}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.onOrderNowClicked(product) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006837), contentColor = Color.White)
            ){
                Text(text = "Check Out")
            }
        }
    }
}

@Composable
fun AssetImage(assetName: String, modifier: Modifier = Modifier) {
    // Load the image from assets
    val context = LocalContext.current
    val bitmap = BitmapFactory.decodeStream(context.assets.open(assetName))

    // Check if bitmap is not null before creating an ImageBitmap
    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    val productRepository = ProductRepository(ProductDataSource())
    val viewModel = ProductsViewModel(GetProductDataUseCase(productRepository))
    ProductsScreen(
        viewModel = viewModel,
        navController = NavController(LocalContext.current))
}