package com.example.weatherapp.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.weatherapp.screens.favorite.FavoriteViewModel
import com.example.weatherapp.widgets.WeatherAppBar
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "About",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController,


        ) {
            navController.popBackStack()
        }
    }) { paddingValues ->  // استفاده از paddingValues برای مدیریت فاصله‌ها
        Surface(modifier = Modifier
            .padding(paddingValues)  // استفاده از paddingValues
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier.padding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "about....")
            }
        }
    }
}
