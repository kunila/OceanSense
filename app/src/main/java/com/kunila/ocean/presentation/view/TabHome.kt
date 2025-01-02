package com.kunila.ocean.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kunila.ocean.presentation.viewmodel.PollutionViewModel
import com.kunila.ocean.presentation.viewmodel.TemperatureViewModel
import com.kunila.ocean.presentation.viewmodel.WindViewModel
import kotlinx.coroutines.launch
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kunila.ocean.presentation.viewmodel.AquaViewModel


data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val subTabs: List<SubTabItem> = emptyList()
)

data class SubTabItem(
    val title: String,
    val unselectedIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null
)

val tabItems = listOf(
    TabItem(
        title = "Pollution",
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.PlayArrow
    ),
    TabItem(
        title = "Wind",
        unselectedIcon = Icons.Outlined.Refresh,
        selectedIcon = Icons.Filled.Add
    ),
    TabItem(
        title = "Temperature",
        unselectedIcon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.AddCircle
    ),
    TabItem(
        title = "AquaSense",
        unselectedIcon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.AddCircle,
        subTabs = listOf(
            SubTabItem("Graph"),
            SubTabItem("Products"))
    )
)

@Composable
fun TabIndicator(color: Color, modifier: Modifier = Modifier) {
    // Color is passed in as a parameter [color]
    Box(
        modifier
            .padding(5.dp)
            .fillMaxSize()
            .drawWithContent {
                drawContent()
                drawLine(
                    color = Color.Blue,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 5f
                )
            }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(pagerState: PagerState) {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page ->
                selectedTabIndex = page
            }
    }

    TopAppBar(
        title = {
            Text(
                text = tabItems[selectedTabIndex].title,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val state by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    var selectedTabIndex by remember {
        mutableIntStateOf(state)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress)
        {
            snapshotFlow { pagerState.currentPage }
                .collect { page ->
                    selectedTabIndex = page
                }
        }
    }

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.White,
        indicator = {
            TabIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.tabIndicatorOffset(selectedTabIndex))
        }
    ) {
        tabItems.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = tabItems[index].title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = if (pagerState.currentPage == index) Color.Blue else Color.Black
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                icon = {
                    Icon(imageVector = tabItems[index].unselectedIcon, contentDescription = null)
                }
            )
        }
    }
}

@Composable
fun TabsContent(pagerState: PagerState, modifier: Modifier = Modifier, navController: NavController) {

    HorizontalPager(state = pagerState , modifier = modifier) { page ->
        when (page) {
            0 -> {
                val viewModel: PollutionViewModel = hiltViewModel()
                PollutionScreen(viewModel = viewModel)
            }
            1 -> {
                val viewModel: WindViewModel = hiltViewModel()
                WindScreen(viewModel = viewModel)
            }
            2 -> {
                val viewModel: TemperatureViewModel = hiltViewModel()
                TemperatureScreen(viewModel = viewModel)
            }
            3 -> {
                val viewModel: AquaViewModel = hiltViewModel()
                AquaScreen(viewModel = viewModel, navController = navController)
            }
        }
    }
}

@Composable
fun TabScreen(pagerState: PagerState,navController: NavController) {
    Column(
        modifier = Modifier.background(Color.White)
    )
    {
        TabsContent(pagerState = pagerState, modifier = Modifier.weight(1f), navController = navController)
        Tabs(pagerState = pagerState)
    }
}

@Composable
fun TabHome(navController: NavController) {
    val pagerState = rememberPagerState {
        tabItems.size
    }
    Column {
        TopBar(pagerState = pagerState)
        TabScreen(pagerState = pagerState, navController = navController)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    TabHome(navController = NavController(LocalContext.current))
}