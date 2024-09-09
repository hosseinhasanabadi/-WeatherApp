package com.example.weatherapp.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.weatherapp.R
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.widgets.WeatherAppBar
import okhttp3.OkHttpClient

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    //


    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData("moscow")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()


    } else if (weatherData.data != null) {
        // Text(text = "main Screen ${weatherData.data.city.country}")
        MainScaffold(
            weather = weatherData.data,
            navController
        )
    }
}

@SuppressLint("ProduceStateDoesNotAssignValue", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavController,
) {
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
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name +" ,${weather.city.country}",

            navController = navController,
            elevation = 5.dp
        )

    }) { paddingValues ->
        MainContent(data = weather, paddingValues=paddingValues)

    }


}

@Composable
fun MainContent(data: Weather,paddingValues: PaddingValues) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    Log.d("WeatherIcon", "Icon URL: $imageUrl")
    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(4.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nov 29",

            style = MaterialTheme.typography.bodySmall
            , color = Color.Black

            , fontWeight = FontWeight.SemiBold
            , modifier = Modifier.padding(6.dp))
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape
            , color = Color(0xFFFFC400)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherStateImage(imageUrl)
                Text(text ="56" ,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold

                )
                Text(text = "Snow", fontStyle = FontStyle.Italic)

            }

        }

    }
}






@Composable
fun WeatherStateImage(
  imageUrl: String
) {
    Image(painter = rememberAsyncImagePainter(imageUrl)

        , contentDescription ="icon image",

        modifier = Modifier.size(50.dp,50.dp)
    )
}



