package com.schuler.termogame.screens.splash


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.navigation.NavController
import androidx.navigation.NavHostController


import com.schuler.termogame.R
import com.schuler.termogame.navigation.AppScreens
import com.schuler.termogame.components.HomeMenuButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController){
    var startAnimation by remember{
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2700
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(3000)
        navController.popBackStack()
        navController.navigate(AppScreens.HomeScreen.name)
    }
    SplashContent(alpha = alphaAnim.value)
}

@Composable
fun SplashContent(alpha: Float){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background

    ){
        Box(
            modifier = Modifier
                .padding(bottom = 100.dp)
                .size(300.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painterResource(id = R.drawable.ic_name_logo_g),
                contentDescription = "Termo Logo",
                modifier = Modifier.alpha(alpha = alpha),
                contentScale = ContentScale.Fit
            )
        }
    }
}