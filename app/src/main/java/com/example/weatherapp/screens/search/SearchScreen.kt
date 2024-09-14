package com.example.weatherapp.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.navigation.WeatherScreens
import com.example.weatherapp.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController){
    Scaffold (topBar = {
        WeatherAppBar(


                    title = "Search",
            navController = navController, icon =
        Icons.AutoMirrored.Filled.ArrowBack,
            isMainScreen = false,){
            navController.popBackStack()
        }
    }) { paddingValues ->
    Surface(modifier = Modifier.padding(paddingValues) ) {
            Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                SearchBar(

                    modifier = Modifier.fillMaxWidth()
                    .padding(6.dp)
                    .align(Alignment.CenterHorizontally)){mCity->
                       navController.navigate(WeatherScreens.MainScreen.name
                               +"/$mCity")
                    }


                
            }

        }


    }

}

@Composable
fun SearchBar(

    modifier:Modifier = Modifier,
    onSearch: (String) -> Unit = {}){
    val SearchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(SearchQueryState.value) {
        SearchQueryState.value.trim().isNotEmpty()
    }

    Column {
        // TextField
        // Button

        CommonTextField(

            valueState = SearchQueryState,
            placeHolder = "Seattle",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(SearchQueryState.value.trim())
                SearchQueryState.value = ""
                keyboardController?.hide()
                
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(

    valueState: MutableState<String>,
    placeHolder: String,
    keyboardType: KeyboardType =
                        KeyboardType.Text,
    imeAction: ImeAction =
                       ImeAction.Next,
    onAction: KeyboardActions= KeyboardActions.Default) {
    OutlinedTextField(value =valueState.value ,
        onValueChange = {valueState.value=it}
    ,label = { Text(text = placeHolder)},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType =
        keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black

        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )


}
