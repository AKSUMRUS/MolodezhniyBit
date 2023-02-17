package com.ledokol.dvor_app.ui.components.atoms.texts

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Subtitle2(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onBackground,
    fontWeight: FontWeight? = MaterialTheme.typography.subtitle2.fontWeight,
    textAlign: TextAlign = TextAlign.Left,
) {

    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.subtitle2,
        modifier = modifier,
        fontWeight = fontWeight,
        textAlign = textAlign,
    )
}

