package com.schuler.termogame.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource


import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.navigation.NavController


import com.schuler.termogame.R
import com.schuler.termogame.navigation.AppScreens
import com.schuler.termogame.components.HomeMenuButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun HomeScreen(navController: NavController){

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
            ){
                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {}
            }
        }
    ){
        MainContent(navController = navController)
    }
}
@Composable
fun MainContent(
    navController: NavController,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column (
            modifier = Modifier
                .padding(bottom = 0.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier.size(250.dp),
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            ) {
                Image(
                    painterResource(id = R.drawable.ic_new_name_logo),
                    contentDescription = "Termo Logo",
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 70.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                HomeMenuButton(
                    label = "Play",
                    borderColor = MaterialTheme.colors.secondary,
                    textColor = MaterialTheme.colors.secondary,
                ){
                    navController.navigate(AppScreens.PlayScreen.name)
                }
                HomeMenuButton(
                    label = "Definições",
                ){
                    navController.navigate(AppScreens.DefinitionScreen.name)
                }

            }
        }

    }

}