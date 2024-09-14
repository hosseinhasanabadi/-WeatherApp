package com.example.weatherapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.model.Favorite
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.screens.favorite.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
   // favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    elevation: Dp = 0.dp,
    navController: NavController,

    onAddActionClicked: () -> Unit = {},
    onAddButtonClicked: () -> Unit = {},
) {

    val showDialog = remember {
        mutableStateOf(false)
    }
    if(showDialog.value){
        ShowSettingDropDownMenu(showDialog = showDialog,navController=navController)
    }
    TopAppBar(title = {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,

            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 15.sp
            )

        )

    }, actions = {
        if (isMainScreen) {
            IconButton(onClick = {
                onAddActionClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )

            }
            IconButton(onClick = {
                showDialog.value= true
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "MoreVert"
                )
            }
        } else Box { }

    }, navigationIcon = {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.clickable {
                    onAddButtonClicked.invoke()
                })


        }
       /* if (isMainScreen) {
            val isAlreadyFavList = favoriteViewModel
                .favList.collectAsState().value.filter { item ->
                    (item.city == title.split(",")[0])
                }

            if (isAlreadyFavList.isNullOrEmpty()) {

                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite icon",
                    modifier = Modifier
                        .scale(0.9f)
                        .clickable {
                            val dataList = title.split(",")
                            favoriteViewModel.insertFavorite(
                                Favorite(
                                    city = dataList[0], // city name
                                    country = dataList[1] // country code
                                )
                            ).run {
                                showDialog.value =true

                            }
                        },
                    tint = Color.Red.copy(alpha = 0.6f))
            }else {
                showDialog.value = false
                Box{}
            }

          //  ShowToast(context = context, showDialog)

        }*/

    },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent
    ), modifier = Modifier.shadow(elevation)
    )


}

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController
) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("About", "Favorites", "Settings")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .background(Color.White)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(
                    text = {
                        Row {
                            Icon(
                                imageVector = when (text) {
                                    "About" -> Icons.Default.Info
                                    "Favorites" -> Icons.Default.Favorite
                                    else -> Icons.Default.Settings
                                },
                                contentDescription = null,
                                tint = Color.LightGray
                            )
                            Text(

                                text = text,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .clickable {
                                        navController.navigate(
                                            when (text) {
                                                "About" -> WeatherScreens.AboutScreen.name
                                                "Favorites" -> WeatherScreens.FavoriteScreen.name
                                                else -> WeatherScreens.SettingsScreen.name
                                            }
                                        )
                                    },

                                fontWeight = FontWeight.Light )// اصلاح FontWeight
                        }
                         },
                    onClick = {  expanded = false
                        showDialog.value = false },

                )
            }
        }
    }
}