package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class PillVariant {
    PRIMARY,
    CYAN,
    CYAN_OUTLINE,
}

@Composable
fun Pill(
    text: String,
    variant: PillVariant,
    modifier: Modifier = Modifier,
) {
    val modifierWithBg = when(variant) {
        PillVariant.PRIMARY -> modifier.background(color = MaterialTheme.colors.primary)
        PillVariant.CYAN -> modifier.background(color = Color.Cyan)
        PillVariant.CYAN_OUTLINE -> modifier.background(color = MaterialTheme.colors.primary)
    }
    Text(text = text, modifier = Modifier.padding(10.dp).clip(shape= CircleShape))
}