package com.schuler.termogame.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeMenuButton(
    label: String,
    borderColor: Color = MaterialTheme.colors.onBackground,
    textColor: Color = MaterialTheme.colors.onSurface,
    style: TextStyle = MaterialTheme.typography.button,
    width: Dp = 200.dp,
    height: Dp = 50.dp,
    maxWidth: Boolean = false,
    onClick: () -> Unit,
){
    Card(
        modifier =
            if (maxWidth){
                Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(height)
                    .border(
                        border = BorderStroke(2.dp, borderColor),
                        shape = RoundedCornerShape(10.dp),
                    )

                    .clickable {
                        onClick()
                    }
            } else {
                Modifier
                    .padding(5.dp)
                    .width(width)
                    .height(50.dp)
                    .border(
                        border = BorderStroke(2.dp, borderColor),
                        shape = RoundedCornerShape(10.dp),
                    )

                    .clickable {
                        onClick()
                    }
            }
        ,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp

    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(
                text = label,
                color = textColor,
                style = style
            )
        }

    }
}