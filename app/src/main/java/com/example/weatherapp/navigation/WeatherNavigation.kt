package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.screens.about.AboutScreen
import com.example.weatherapp.screens.favorite.FavoritesScreen
import com.example.weatherapp.screens.main.MainScreen
import com.example.weatherapp.screens.main.MainViewModel
import com.example.weatherapp.screens.search.SearchScreen
import com.example.weatherapp.screens.settings.SettingsScreen

import com.example.weatherapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController= navController)
        }
        //
        val rout =WeatherScreens.MainScreen.name
        composable("$rout/{city}"
        , arguments = listOf(
            navArgument(name = "city"){
                type = NavType.StringType
            }
        )
        ){
            navBack->
            navBack.arguments?.getString("city").let {city->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController= navController,mainViewModel,
                    city=city)
            }

        }
        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController= navController)
        }
        composable(WeatherScreens.AboutScreen.name){
         AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name){
            FavoritesScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController = navController)
        }



    }

}