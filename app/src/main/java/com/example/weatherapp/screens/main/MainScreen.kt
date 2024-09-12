package com.example.weatherapp.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R

import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherItem
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.formatDecimals
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
        value = mainViewModel.getWeatherData("tehran")
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
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Log.d("WeatherIcon", "Icon URL: $imageUrl")
    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(4.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //date
        Text(text = formatDate (weatherItem.dt),// date today

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

                //temperature
                Text(text =formatDecimals(weatherItem.main.temp) + "°",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold

                )
                //snow and ...
                Text(text = weatherItem.weather[0].main
                    , fontStyle = FontStyle.Italic)

            }

        }

        HumidityWindPressureRow(weather=weatherItem)
        HorizontalDivider()
        //TODO api premium
      //  SunSetSunRiseRow(weather = data.list[0])
        Text(text = "this week",
            style = MaterialTheme.typography.titleMedium
                ,fontWeight=FontWeight.Bold)
        Surface(modifier =
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        , color = Color(0xFFEEF1EF)
            , shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                // فیلتر کردن برای حذف رکوردهای تکراری هر روز
                val uniqueWeatherItems = data.list.groupBy { formatDate(it.dt).split(",")[0] }
                    .map { it.value.first() } // انتخاب اولین رکورد از هر روز

                // نمایش هر آیتم در لیست
                items(items = uniqueWeatherItems) { item: WeatherItem ->
                    WeatherDetailRow(weatherItem = item)
                }
            }



        }
    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun WeatherDetailRow(weatherItem: WeatherItem) {
    val day = formatDate(weatherItem.dt).split(",")[0] // استخراج روز هفته از تاریخ
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}@2x.png"

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // نمایش روز
            Text(
                text = day,
                modifier = Modifier.padding(start = 5.dp)
            )

            // نمایش تصویر وضعیت آب‌وهوا
            WeatherStateImage(imageUrl = imageUrl)
        }
    }
}




//TODO api premium

/*@Composable
fun SunSetSunRiseRow(weather:WeatherItem) {

    Row (modifier = Modifier
        .fillMaxWidth()

        .padding(
            top = 15.dp,
            bottom = 6.dp
        )
        , horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row {
            Image(painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise icon"
                , modifier = Modifier.size(25.dp))
            Text(text = formatDateTime(weather.sys.toString()),
                style = MaterialTheme.typography.bodySmall)

            
        }
        Row {
            Image(painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset icon"
                , modifier = Modifier.size(25.dp))
            Text(text = formatDateTime(weather.sys),
                style = MaterialTheme.typography.bodySmall)


        }
    }
}*/

@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row (modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
        , horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (modifier = Modifier.padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.humidity)
                , contentDescription ="icon humidity ", modifier = 
            Modifier.size(20.dp))
            Text(text = "${weather.main.humidity}%",
                style = MaterialTheme.typography.bodySmall)

        }
        Row (){
            Icon(painter = painterResource(id = R.drawable.pressure)
                , contentDescription ="icon pressure ", modifier =
                Modifier.size(20.dp))
            Text(text = "${weather.main.pressure} psi%",
                style = MaterialTheme.typography.bodySmall)

        }
        Row (){
            Icon(painter = painterResource(id = R.drawable.wind)
                , contentDescription ="icon wind ", modifier =
                Modifier.size(20.dp))
            Text(text = "${weather.main.humidity} mph%",
                style = MaterialTheme.typography.bodySmall)

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


