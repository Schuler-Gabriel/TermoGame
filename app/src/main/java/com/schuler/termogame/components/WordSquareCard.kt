package com.schuler.termogame.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WordSquareCard(
    modifier: Modifier = Modifier,
    activeField: String,
    wordList: List<String>,
    matchingLetterState: List<Int>,
    lineNumber: String,
    activeLine: String,
    activeFieldChange: (Int) -> Unit
){

    Row(
        modifier = modifier
            .padding(end = 5.dp, top = 5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        for (i in 0..4) {
            LetterField(
                letter = wordList[i],
                letterState = matchingLetterState[i],
                activeField,
                fieldNumber = i.toString(),
                isActiveLine = lineNumber == activeLine
            ) {
                activeFieldChange(i)
            }
        }


    }
}
@Composable
fun LetterField(
    letter: String,
    letterState: Int,
    isActive: String,
    fieldNumber: String,
    isActiveLine: Boolean,
    onValueChange: () -> Unit
){


    Card(
        modifier = Modifier
            .padding(start = 5.dp)
            .width(65.dp)
            .height(65.dp)
            .border(
                border = BorderStroke(
                    if (isActive == fieldNumber && isActiveLine ) {
                        4.dp
                    } else {
                        2.dp
                    },

                    if (isActive == fieldNumber && isActiveLine)  {
                        MaterialTheme.colors.onPrimary
                    } else {
                        MaterialTheme.colors.onBackground
                    }
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                enabled = isActiveLine,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onValueChange() }
        ,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = if (isActiveLine) {
            MaterialTheme.colors.background
        } else {
            if(letterState != 0 ){
                if(letterState == 2 ){
                    MaterialTheme.colors.secondaryVariant
                } else {
                    MaterialTheme.colors.error
                }
            }else{
                MaterialTheme.colors.onBackground
            }



        }
    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(
                text = letter,
                style = MaterialTheme.typography.body1
            )
        }
    }
}