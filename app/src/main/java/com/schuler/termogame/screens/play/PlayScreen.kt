package com.schuler.termogame.screens.play

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.schuler.termogame.R
import com.schuler.termogame.components.DialogWord
import com.schuler.termogame.components.Keyboard

import com.schuler.termogame.components.WordSquareCard
import com.schuler.termogame.model.Word
import com.schuler.termogame.screens.definitions.DefinitionsContent
import com.schuler.termogame.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.Normalizer

@Composable
fun PlayScreen(homeViewModel: HomeViewModel, navController: NavHostController) {
    val snackbarHostState = remember{ SnackbarHostState() }
    Scaffold {
            GameContent(homeViewModel, snackbarHostState, navController)
    }
}


@Composable
fun GameContent(
    homeViewModel: HomeViewModel,
    snackbarHostState: SnackbarHostState,
    navController: NavHostController
) {
    val gameWord = homeViewModel.randomWord.collectAsState()

    println(gameWord.value)

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TopAppBar(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .height(70.dp)
                    .fillMaxWidth(),
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
                    Box(
                        modifier = Modifier.width( 200.dp),
                        contentAlignment = Alignment.Center

                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_header_logo),
                            contentDescription = "Termo Logo",
                            contentScale = ContentScale.Fit
                        )
                    }
                    Box(){}
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                for (i in 0..5) {
                    WordSquareCard(
                        activeField = homeViewModel.activeField.value,
                        wordList = homeViewModel.wordList[i],
                        matchingLetterState = homeViewModel.matchingLetterState[i],
                        lineNumber = i,
                        activeLine = homeViewModel.activeLine.value,
                    ) { lineIndex ->
                        homeViewModel.activeField.value = lineIndex
                    }
                }

                Keyboard(
                    wordCheckHandler = {
                        homeViewModel.wordCheckHandler(snackbarHostState)
                    },
                    alphabet = homeViewModel.alphabet
                ) { key ->
                    if (key == "back") {
                        if (homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value] != "") {
                            homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value] =
                                ""
                        } else {
                            if (homeViewModel.activeField.value != 0) {
                                homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value - 1] =
                                    ""
                                homeViewModel.activeField.value =
                                    homeViewModel.activeField.value - 1
                            }
                        }
                    } else {
                        if (homeViewModel.activeField.value == 4) {
                            homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value] =
                                key
                            for (i in 0..4) {
                                if (homeViewModel.wordList[homeViewModel.activeLine.value][i] == "") {
                                    homeViewModel.activeField.value = i
                                    break
                                }
                            }
                        } else {
                            homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value] =
                                key
                            homeViewModel.activeField.value =
                                homeViewModel.activeField.value.toInt() + 1
                        }
                    }

                }

            }
        }
    }
    AnimatedVisibility(
        visible = homeViewModel.wordDialogRight.value,
        enter = slideInVertically(initialOffsetY = { 2000 }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -2000 }) + fadeOut()
    ) {
        DialogWord(homeViewModel = homeViewModel, type = true)
    }
    AnimatedVisibility(
        visible = homeViewModel.wordDialogWrong.value,
        enter = slideInVertically(initialOffsetY = { 2000 }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -2000 }) + fadeOut()
    ) {
        DialogWord(homeViewModel = homeViewModel, type = false)
    }



    SnackbarHost(hostState = snackbarHostState) {
        Snackbar(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            backgroundColor = Color(0xFF4443A8),
            shape = RoundedCornerShape(10.dp),
            action = {
                Card(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            snackbarHostState.currentSnackbarData?.dismiss()
                        },
                    border = BorderStroke(2.dp, Color(0xFFBDBDBD)),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color(0xFF4443A8),
                    elevation = 10.dp,

                    ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it.actionLabel!!,
                            style = MaterialTheme.typography.h2,
                            color = Color(0xFFBDBDBD)

                        )
                    }
                }
            }
        ) {
            Row(
                modifier = Modifier.padding(3.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = it.message,
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFFBDBDBD)

                )
            }

        }
    }
}

