package com.kunila.ocean.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kunila.ocean.data.datasource.ProductDataSource
import com.kunila.ocean.data.repository.ProductRepository
import com.kunila.ocean.domain.model.ProductData
import com.kunila.ocean.domain.usecase.GetProductDataUseCase
import com.kunila.ocean.presentation.view.AquaScreen
import com.kunila.ocean.presentation.view.ContactScreen
import com.kunila.ocean.presentation.view.GraphScreen
import com.kunila.ocean.presentation.view.ProductsScreen
import com.kunila.ocean.presentation.view.SplashScreen
import com.kunila.ocean.presentation.view.TabHome
import com.kunila.ocean.presentation.viewmodel.AquaViewModel
import com.kunila.ocean.presentation.viewmodel.ContactViewModel
import com.kunila.ocean.presentation.viewmodel.GraphViewModel
import com.kunila.ocean.presentation.viewmodel.ProductsViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    )
    {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screens.TabScreen.route) {
            TabHome(navController)
        }
        composable(Screens.AquaScreen.route) {
            AquaScreen(AquaViewModel(), navController)
        }
        composable(Screens.ProductsScreen.route) {
            ProductsScreen(ProductsViewModel(
                GetProductDataUseCase(ProductRepository(ProductDataSource()))
            ),navController)
        }
        composable(Screens.GraphScreen.route) {
            GraphScreen(GraphViewModel())
        }
        composable(Screens.ContactScreen.route) {
            ContactScreen(ContactViewModel(),navController)
        }
    }
}