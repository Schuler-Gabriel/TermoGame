package com.schuler.termogame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun RatingChart(
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
                text = "Taxa de Aproveitamento:",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2
            )
        }

        for (i in wins.indices) {
//            Chart(legend = legend[i], proportions = if(wins[i] + loses[i] != 0f) wins[i]/(wins[i] + loses[i]) else 0f)
        }
        Row(
            modifier = Modifier
                .padding(10.dp, 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(thickness = 1.dp, color = MaterialTheme.colors.onBackground)
        }
//        Chart(legend = "Total", proportions = if(wins.sum() + loses.sum() != 0f) wins.sum()/(wins.sum() + loses.sum()) else 0f, total = true)

    }
}

//@Composable
//fun Chart( legend: String, proportions: Float, total: Boolean = false) {
//    val configuration = LocalConfiguration.current
//    val barWidth = configuration.screenWidthDp * 0.65
//    Row(
//        modifier = Modifier
//            .padding(10.dp, 10.dp)
//            .fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        Text(
//            text = legend,
//            color = if(!total) MaterialTheme.colors.onBackground else MaterialTheme.colors.secondary,
//            style = MaterialTheme.typography.body2
//        )
//
//        Spacer(modifier = Modifier.width(4.dp))
//
//        Box(
//            modifier = Modifier
//                .width(barWidth.dp)
//                .height(20.dp)
//                .background(
//                    color = MaterialTheme.colors.onSurface,
//                    shape = RoundedCornerShape(10.dp)
//                )
//        ){
//            Box(
//                modifier = Modifier
//                    .width(((barWidth * proportions) + configuration.screenWidthDp * 0.1).dp)
//                    .height(25.dp)
//                    .background(
//                        color = if (total) MaterialTheme.colors.secondary else {
//                            if (proportions >= 0.5) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
//                        },
//                        shape = RoundedCornerShape(10.dp)
//                    ),
//                contentAlignment = Alignment.CenterEnd,
//            ){
//                Text(
//                    modifier = Modifier
//                        .padding(0.dp, 0.dp, 5.dp, 0.dp),
//                    text = String.format("%.0f", (proportions * 100)) + "%",
//                    color = MaterialTheme.colors.onBackground,
//                    style = MaterialTheme.typography.body2
//                )
//            }
//        }
//    }
//}
