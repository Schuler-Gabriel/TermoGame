package com.schuler.termogame.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    linkName: String,
    Hyperlink: String,
){
    val annotatedString = buildAnnotatedString{
           addStringAnnotation(
               tag = "URL",
               annotation = Hyperlink,
               start = 0,
               end = linkName.length
           )
    }
    val uriHandler = LocalUriHandler.current
    Text(
        text = linkName,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier.clickable{
            annotatedString
                .getStringAnnotations(tag = "URL", start = 0, end = linkName.length)
                .firstOrNull()?.let {stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}