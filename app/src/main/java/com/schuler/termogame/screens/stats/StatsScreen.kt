package com.schuler.termogame.screens.stats


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.schuler.termogame.R
import com.schuler.termogame.components.AdaptiveBanner
import com.schuler.termogame.components.RatingChart
import com.schuler.termogame.components.StatsButton
import com.schuler.termogame.components.TotalStats
import com.schuler.termogame.screens.home.HomeViewModel


@Composable
fun StatsScreen(homeViewModel: HomeViewModel, navController: NavHostController){
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {
            BottomAppBar() {
                AdaptiveBanner("ca-app-pub-9867287983655960/4544705810")
            }
        }
    ){
        Column{
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
            StatsContent(homeViewModel)
        }

    }
}

@Composable
fun StatsContent(homeViewModel: HomeViewModel){

    val wins = homeViewModel.wins.collectAsState()
    val losses = homeViewModel.losses.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
//            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment= Alignment.CenterHorizontally,
            ) {
            TotalStats(wins.value, losses.value)
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Obs: A barra de aproveitamento mostra a porcentagem de acertos que você obteve.",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify,
            )

        }
    }
}
