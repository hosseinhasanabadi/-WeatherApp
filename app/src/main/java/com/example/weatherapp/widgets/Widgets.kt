package com.example.weatherapp.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherItem
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.formatDecimals


@SuppressLint("SuspiciousIndentation")
@Composable
fun WeatherDetailRow(weatherItem: WeatherItem) {
    val day = formatDate(weatherItem.dt).split(",")[0] // استخراج روز هفته از تاریخ
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

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
            Surface(modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(text = weatherItem.weather[0].description
                    , modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodySmall)


            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.SemiBold
                )
                ){
                    append(formatDecimals(weatherItem.main.temp_max) + "°")

                }
                withStyle(style = SpanStyle(
                    color = Color.LightGray
                )
                ){
                    append(formatDecimals(weatherItem.main.temp) +"°")
                }
            })
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


