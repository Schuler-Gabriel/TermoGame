package com.schuler.termogame.screens.stats


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.schuler.termogame.R
import com.schuler.termogame.components.DoughnutChart
import com.schuler.termogame.components.StatsButton
import com.schuler.termogame.screens.definitions.DefinitionsContent
import com.schuler.termogame.screens.home.HomeViewModel


@Composable
fun StatsScreen(homeViewModel: HomeViewModel, navController: NavHostController){
    Scaffold{
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
    val difficulty = homeViewModel.difficulty.collectAsState()
    var isActive = remember {
        mutableStateOf(difficulty.value)
    }
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Column(
                modifier = Modifier.padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DoughnutChart()
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    StatsButton(
                        label = "Facíl",
                        isActive = isActive.value == 1
                    ) { isActive.value = 1 }

                    StatsButton(
                        label = "Difícil",
                        isActive = isActive.value == 3
                    ) { isActive.value = 3 }

                }
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    StatsButton(
                        label = "Médio",
                        isActive = isActive.value == 2
                    ) { isActive.value = 2 }
                    StatsButton(
                        label = "Aleatório",
                        isActive = isActive.value == 4
                    ) { isActive.value = 4 }

                }
            }
        }
    }
}