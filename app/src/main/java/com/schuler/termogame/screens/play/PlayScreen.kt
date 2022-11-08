package com.schuler.termogame.screens.play

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.schuler.termogame.R
import com.schuler.termogame.components.Keyboard

import com.schuler.termogame.components.WordSquareCard
import com.schuler.termogame.model.Word
import com.schuler.termogame.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.Normalizer

@Composable
fun PlayScreen(homeViewModel: HomeViewModel, navController: NavController) {
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

                    Box(){}
                }
            }
        }
    ) {
        GameContent(homeViewModel)
    }
}


@Composable
fun GameContent(homeViewModel: HomeViewModel){
    val gameWord = homeViewModel.randomWord.collectAsState()
    val searchedWordExistes = homeViewModel.searchedWordExistes.collectAsState()

    println(gameWord.value)


    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            { homeViewModel.activeField.value = "" },
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (i in 0..5) {
                WordSquareCard(
                    activeField = homeViewModel.activeField.value,
                    wordList = homeViewModel.wordList[i],
                    matchingLetterState = homeViewModel.matchingLetterState[i],
                    lineNumber = i.toString(),
                    activeLine = homeViewModel.activeLine.value.toString(),
                ) { lineIndex ->
                    homeViewModel.activeField.value = lineIndex.toString()
                }
            }

            Keyboard(
                wordCheckHandler = {
                        homeViewModel.wordCheckHandler()
                }
            ){key ->
                if (key == "back") {
                    if (homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value.toInt()] != "") {
                        homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value.toInt()] = ""
                    } else {
                        if (homeViewModel.activeField.value != "0") {
                            homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value.toInt() - 1] = ""
                            homeViewModel.activeField.value = (homeViewModel.activeField.value.toInt() - 1).toString()
                        }
                    }
                } else {
                    if (homeViewModel.activeField.value == "4") {
                        homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value.toInt()] = key
                        for (i in 0..4){
                            if (homeViewModel.wordList[homeViewModel.activeLine.value][i] == "" ){
                                homeViewModel.activeField.value = i.toString()
                                break
                            }
                        }
                    } else {
                        homeViewModel.wordList[homeViewModel.activeLine.value][homeViewModel.activeField.value.toInt()] = key
                        homeViewModel.activeField.value = (homeViewModel.activeField.value.toInt() + 1).toString()
                    }
                }

            }

        }
    }
}