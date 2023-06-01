package com.schuler.termogame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun TotalStats(
    wins: List<Float>,
    loses: List<Float>,
) {
    val legend = listOf( "Fácil", "Médio", "Difícil", "Aleatório")

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment= Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp, 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total de partidas e aproveitamento:",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2
            )
        }

        for (i in wins.indices) {
            Total(legend = legend[i], matches = wins[i] + loses[i])
            Chart(proportions = if(wins[i] + loses[i] != 0f) wins[i]/(wins[i] + loses[i]) else 0f)
        }
        Row(
            modifier = Modifier
                .padding(10.dp, 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(thickness = 1.dp, color = MaterialTheme.colors.onBackground)
        }
        Total(legend = "Total", matches = wins.sum() + loses.sum(), total = true)
        Chart(proportions = if(wins.sum() + loses.sum() != 0f) wins.sum()/(wins.sum() + loses.sum()) else 0f, total = true)

    }
}

@Composable
fun Total( legend: String, matches: Float, total: Boolean = false) {
    Row(
        modifier = Modifier
            .padding(10.dp, 10.dp, 10.dp, 0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = legend,
            color = if(!total) MaterialTheme.colors.onBackground else MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body2
        )

        Text(
            modifier = Modifier
                .padding(0.dp, 0.dp, 5.dp, 0.dp),
            text = String.format("%.0f partidas jogadas", matches),
            color = if(!total) MaterialTheme.colors.onBackground else MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body2
        )

    }
}

@Composable
fun Chart(proportions: Float, total: Boolean = false) {
    val configuration = LocalConfiguration.current
    val barWidth = configuration.screenWidthDp  * 0.9
    Row(
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(configuration.screenWidthDp.dp)
                .height(25.dp)
                .background(
                    color = MaterialTheme.colors.onSurface,
                    shape = RoundedCornerShape(13.dp)
                )
        ){
            Box(
                modifier = Modifier
                    .width(((barWidth * proportions) + configuration.screenWidthDp  * 0.1).dp)
                    .height(25.dp)
                    .background(
                        color = if (total) MaterialTheme.colors.secondary else {
                            if (proportions >= 0.5) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
                        },
                        shape = RoundedCornerShape(13.dp)
                    ),
                contentAlignment = Alignment.CenterEnd,
            ){
                Text(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 7.dp, 0.dp),
                    text = String.format("%.0f", (proportions * 100)) + "%",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}