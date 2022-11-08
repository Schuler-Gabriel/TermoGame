package com.schuler.termogame.screens.definitions

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.schuler.termogame.R
import com.schuler.termogame.components.HomeMenuButton
import com.schuler.termogame.navigation.AppScreens
import com.schuler.termogame.screens.home.HomeViewModel
import com.schuler.termogame.screens.play.GameContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DefinitionScreen(homeViewModel: HomeViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Card(
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        },
                        backgroundColor = Color.Transparent,
                        elevation = 0.dp,
                    ) {
                        Icon(
                            Icons.Outlined.ArrowBackIos,
                            contentDescription = "back",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }

                    Image(
                        painterResource(id = R.drawable.ic_header_logo),
                        contentDescription = "Termo Logo",
                        modifier = Modifier.padding(end = 27.dp),
                        contentScale = ContentScale.Fit
                    )

                    Box() {}
                }
            }
        }
    ) {
        DefinitionsContent(homeViewModel)
    }
}

@Composable
fun DefinitionsContent(homeViewModel: HomeViewModel){
    val difficulty = homeViewModel.difficulty.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Column (
            modifier = Modifier
                .padding(vertical = 10.dp)
        ){
            Text(
                modifier = Modifier.padding(horizontal = 30.dp),
                text = "Dificuldade:",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onSurface
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 30.dp),
            ) {
                HomeMenuButton(
                    label = "Fácil",
                    style = MaterialTheme.typography.caption,

                    borderColor = if (difficulty.value == 1) MaterialTheme.colors.secondary
                    else MaterialTheme.colors.onSurface,

                    textColor = if (difficulty.value == 1) MaterialTheme.colors.secondary
                    else MaterialTheme.colors.onSurface,

                    width = 100.dp
                ) {
                    homeViewModel.saveDifficulty(1)
                }
                HomeMenuButton(
                    label = "Médio",
                    style = MaterialTheme.typography.caption,

                    borderColor = if (difficulty.value == 2) MaterialTheme.colors.secondary
                    else MaterialTheme.colors.onSurface,

                    textColor = if (difficulty.value == 2) MaterialTheme.colors.secondary
                    else MaterialTheme.colors.onSurface,

                    width = 100.dp
                ) {
                    homeViewModel.saveDifficulty(2)
                }
                HomeMenuButton(
                    label = "Difício",
                    style = MaterialTheme.typography.caption,

                    borderColor = if (difficulty.value == 3) MaterialTheme.colors.secondary
                    else MaterialTheme.colors.onSurface,

                    textColor = if (difficulty.value == 3) MaterialTheme.colors.secondary
                    else MaterialTheme.colors.onSurface,

                    width = 100.dp
                ) {
                    homeViewModel.saveDifficulty(3)
                }
            }
        }



    }
}
