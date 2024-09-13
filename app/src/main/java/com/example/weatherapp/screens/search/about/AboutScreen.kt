package com.example.weatherapp.screens.search.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.weatherapp.R
import com.example.weatherapp.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "About",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController){
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Column(Modifier.padding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "about....")

            }

        }


    }

}