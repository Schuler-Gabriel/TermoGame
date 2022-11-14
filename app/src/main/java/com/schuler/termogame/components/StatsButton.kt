package com.schuler.termogame.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StatsButton(
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
){
    Card(
        modifier = Modifier
                .padding(5.dp)
                .border(
                    border = BorderStroke(2.dp, if (isActive) MaterialTheme.colors.secondary else MaterialTheme.colors.background),
                    shape = RoundedCornerShape(10.dp),
                )
                .clickable {
                    onClick()
                }
        ,
        backgroundColor = MaterialTheme.colors.background,
        elevation = if (isActive) 10.dp else 0.dp

    ) {

            Text(
                text = label,
                color = if (isActive) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(5.dp),
            )


    }
}