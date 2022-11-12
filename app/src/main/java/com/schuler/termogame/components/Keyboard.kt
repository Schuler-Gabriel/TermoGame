package com.schuler.termogame.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Keyboard (
    modifier: Modifier = Modifier,
    wordCheckHandler: () -> Unit,
    alphabet: Map<String, Int>,
    press: (String) -> Unit
) {
    val lettersList = listOf(
        "Q","W","E","R","T","Y","U","I","O","P",
        "A","S","D","F","G","H","J","K","L",
        "Z","X","C","V","B","N","M","back","send"
    )

    Surface(
        modifier = modifier
            .padding(top = 30.dp, end = 5.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Column{
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                for (i in lettersList.slice(0..9)){
                    KeyButton(key = i, alphabet) {
                        press(i)
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                for (i in lettersList.slice(10..18)){
                    KeyButton(key = i, alphabet) {
                        press(i)
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                for (i in lettersList.slice(19..26)){
                    KeyButton(key = i, alphabet) {
                        press(i)
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                horizontalArrangement = Arrangement.Center
            ){
                    SendButton() {
                        wordCheckHandler()
                    }
            }
        }

    }
}

@Composable
fun SendButton(
    press: () -> Unit
){
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(40.dp)
            .border(
                border = BorderStroke(2.dp, MaterialTheme.colors.secondary),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { press() },
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(10.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

                Text(
                    text = "Enviar",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.secondary
                )

        }
    }
}

@Composable
fun KeyButton(
    key: String,
    alphabet: Map<String, Int>,
    press: () -> Unit
){
    Card(
        modifier = Modifier
            .padding(start = (if (key == "back") 10.dp else 5.dp))
            .width(if (key == "back") 43.dp else 28.dp)
            .height(40.dp)

            .clickable { press() },
        backgroundColor = when(alphabet[key]) {
            0 -> MaterialTheme.colors.background
            1 -> MaterialTheme.colors.error
            2 -> MaterialTheme.colors.secondaryVariant
            10 -> MaterialTheme.colors.onBackground
            else -> {MaterialTheme.colors.onBackground}
        },
        border = BorderStroke(2.dp,  if(alphabet[key] == 0) MaterialTheme.colors.onBackground else Color.Transparent),
        shape = RoundedCornerShape(10.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (key == "back"){
                Icon(
                    Icons.Outlined.Backspace,
                    contentDescription = "backspace",
                    modifier = Modifier.size(25.dp).padding(end = 2.dp),
                    tint = Color.White
                )
            }else {
                Text(
                    text = key,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}