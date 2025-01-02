package com.kunila.ocean.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kunila.ocean.presentation.viewmodel.AquaViewModel
import com.kunila.ocean.presentation.viewmodel.GraphViewModel
import com.kunila.ocean.presentation.viewmodel.ProductsViewModel
import kotlinx.coroutines.launch

@Composable
fun AquaScreen(viewModel: AquaViewModel, navController: NavController){

    val subTabs = listOf(
        SubTabItem("Graph"),
        SubTabItem("Products"))
    val innerPagerState = rememberPagerState(pageCount = { subTabs.size })
    val scope = rememberCoroutineScope()
    val state by remember { mutableIntStateOf(0) }
    var selectedTabIndex by remember {
        mutableIntStateOf(state)
    }

    LaunchedEffect(innerPagerState.currentPage, innerPagerState.isScrollInProgress) {
        if(!innerPagerState.isScrollInProgress)
        {
            snapshotFlow { innerPagerState.currentPage }
                .collect { page ->
                    selectedTabIndex = page
                }
        }
    }

    Column {
        TabRow(selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            indicator = { tabPositions ->
                TabIndicator(
                    MaterialTheme.colorScheme.primary,
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            }) {
            subTabs.forEachIndexed { index, item ->
                Tab(
                    text = {
                        Text(
                            item.title,
                            color = if (innerPagerState.currentPage == index) Color.Blue else Color.Black
                        )},
                    selected = innerPagerState.currentPage == index,
                    onClick = { scope.launch { innerPagerState.animateScrollToPage(index) } }
                )
            }
        }

        HorizontalPager(state = innerPagerState) { page ->
            when (page) {
                0 -> {
                    val viewModel: GraphViewModel = hiltViewModel()
                    GraphScreen(viewModel = viewModel)
                }
                1 -> {
                    val viewModel: ProductsViewModel = hiltViewModel()
                    ProductsScreen(viewModel = viewModel, navController = navController)
                }
            }
        }
    }
}

