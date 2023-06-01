package com.schuler.termogame.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.schuler.termogame.screens.home.HomeViewModel

@Composable
fun DialogWord (homeViewModel: HomeViewModel, type: Boolean){

    var word = homeViewModel.randomWord.collectAsState()
    var color = if (type) MaterialTheme.colors.secondary else MaterialTheme.colors.onPrimary
    var title = if (type) "Parabéns, você acertou a palavra:" else "Desculpe, mas a palavra era:"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent,



        ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Card(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .border(4.dp, color, RoundedCornerShape(20.dp))
                    .height(300.dp)
                    .fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = word.value.name,
                        style = MaterialTheme.typography.body1,
                        color = color
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                        ,
                        onClick = { homeViewModel.wordDialogFunction(type) },
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(2.dp, color),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground)
                    ) {
                        Text("OK")
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))

        }
    }

}