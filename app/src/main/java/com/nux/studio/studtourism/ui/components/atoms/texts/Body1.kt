package com.nux.studio.studtourism.ui.components.atoms.texts

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.nux.studio.studtourism.ui.theme.fontFamily

@Composable
fun Body1(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onBackground,
    fontWeight: FontWeight? = MaterialTheme.typography.body1.fontWeight,
    textAlign: TextAlign = TextAlign.Left,
    fontSize: TextUnit = MaterialTheme.typography.body1.fontSize,
) {

    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.body1,
        modifier = modifier,
        fontWeight = fontWeight,
        textAlign = textAlign,
        fontSize = fontSize,
    )
}