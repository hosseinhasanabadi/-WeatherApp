package com.example.weatherapp.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.widgets.WeatherAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    //


    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData("Seattle")
    }.value

    if (weatherData.loading==true){
        CircularProgressIndicator()


    }else if (weatherData.data!=null){
        // Text(text = "main Screen ${weatherData.data.city.country}")
        MainScaffold(mainViewModel ,weather = weatherData.data,
            navController)
    }
}

@SuppressLint("ProduceStateDoesNotAssignValue", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(mainViewModel: MainViewModel, weather: Weather,navController: NavController) {
  /*  val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData("Seattle")
    }.value

    if (weatherData.loading==true){
        CircularProgressIndicator()


    }else if (weatherData.data!=null){
       // Text(text = "main Screen ${weatherData.data.city.country}")
    }*/
Scaffold(topBar ={
    WeatherAppBar(title = "Helena,MT")
} ) {
    MainContent(data= weather)

}


}

@Composable
fun MainContent(data: Weather) {
    Text(text =data.city.name)

}
