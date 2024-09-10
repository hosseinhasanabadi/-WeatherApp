package com.example.weatherapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun WeatherAppBar (
    title:String ="Title",
    icon:ImageVector?=null,
    isMainScreen:Boolean = true,

    elevation: Dp =0.dp,
   navController: NavController,
    onAddActionClicked:()->Unit={},
    onAddButtonClicked:()->Unit={}, ){
    TopAppBar(title = {
        Text(text = title,
            color = MaterialTheme.colorScheme.onBackground,

            style = TextStyle(fontWeight = FontWeight.Bold
            , fontSize = 15.sp)

            )

    }, actions ={
        if (isMainScreen){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search")
                
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.MoreVert,
                    contentDescription = "MoreVert")
            }
        }else Box { }

    }, navigationIcon ={
        if (icon !=null){
            Icon(imageVector = icon, contentDescription = null,
                tint =MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.clickable {
                    onAddButtonClicked.invoke()
                })
        }
    }  ,colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent), modifier =Modifier.shadow(elevation)
    )

    
}