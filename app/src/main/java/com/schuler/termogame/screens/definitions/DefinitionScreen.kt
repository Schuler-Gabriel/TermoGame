package com.schuler.termogame.screens.definitions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.schuler.termogame.R
import com.schuler.termogame.components.HomeMenuButton
import com.schuler.termogame.components.HyperlinkText
import com.schuler.termogame.navigation.AppScreens
import com.schuler.termogame.screens.home.HomeViewModel
import com.schuler.termogame.screens.play.GameContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DefinitionScreen(homeViewModel: HomeViewModel, navController: NavHostController) {
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
            DefinitionsContent(homeViewModel)
        }

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
        Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    text = "Dificuldade:",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onSurface
                )
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)

                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 20.dp, bottom = 2.dp)
                    ) {
                        HomeMenuButton(
                            label = "Fácil",
                            style = MaterialTheme.typography.caption,

                            borderColor = if (difficulty.value == 1) MaterialTheme.colors.secondary
                            else MaterialTheme.colors.onSurface,

                            textColor = if (difficulty.value == 1) MaterialTheme.colors.secondary
                            else MaterialTheme.colors.onSurface,

                            width = 95.dp,
                            height = 60.dp
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

                            width = 80.dp,
                            height = 60.dp
                        ) {
                            homeViewModel.saveDifficulty(2)
                        }
                        HomeMenuButton(
                            label = "Difícil",
                            style = MaterialTheme.typography.caption,


                            borderColor = if (difficulty.value == 3) MaterialTheme.colors.secondary
                            else MaterialTheme.colors.onSurface,

                            textColor = if (difficulty.value == 3) MaterialTheme.colors.secondary
                            else MaterialTheme.colors.onSurface,

                            width = 95.dp,
                            height = 60.dp
                        ) {
                            homeViewModel.saveDifficulty(3)
                        }
                    }

                    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                        HomeMenuButton(
                            label = "Aleatório",
                            style = MaterialTheme.typography.caption,


                            borderColor = if (difficulty.value == 4) MaterialTheme.colors.secondary
                            else MaterialTheme.colors.onSurface,

                            textColor = if (difficulty.value == 4) MaterialTheme.colors.secondary
                            else MaterialTheme.colors.onSurface,

                            maxWidth = true,
                            height = 60.dp
                        ) {
                            homeViewModel.saveDifficulty(4)
                        }
                    }
                }

            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding( vertical = 15.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth().padding(bottom = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .height(2.dp)
                        .weight(1f)
                        .background(MaterialTheme.colors.onBackground)) {}
                    Text(
                        text = "Sobre o Jogo",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Box(modifier = Modifier
                        .height(2.dp)
                        .weight(1f)
                        .background(MaterialTheme.colors.onBackground)) {}
                }
                Text(
                    text = "Versão 1.0.0",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = "Developed by Gabriel Schuler",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(bottom = 2.dp)
                )

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    HyperlinkText(
                        linkName = "Política de privacidade",
                        Hyperlink = "https://termo-game.blogspot.com/2022/11/privacy-policy-para-idioma-portugues.html"
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .height(15.dp)
                            .width(1.dp)
                            .background(color = MaterialTheme.colors.onBackground)
                    )
                    HyperlinkText(
                        linkName = "Termos de uso",
                        Hyperlink = "https://termo-game.blogspot.com/2022/11/terms-conditions-para-idioma-portugues.html"
                    )


                }
            }
            
        }
    }
}

