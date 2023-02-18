package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class PillVariant {
    PRIMARY,
    OUTLINE,
}

@Composable
fun Pill(
    text: String,
    modifier: Modifier = Modifier,
    variant: PillVariant = PillVariant.OUTLINE,
) {
    val modifierWithBg = when (variant) {
        PillVariant.PRIMARY -> modifier.background(color = MaterialTheme.colors.primary)
        PillVariant.OUTLINE -> modifier.background(color = MaterialTheme.colors.background)
    }
    val textColor = when (variant) {
        PillVariant.PRIMARY -> MaterialTheme.colors.onPrimary
        PillVariant.OUTLINE -> MaterialTheme.colors.onBackground
    }
    Box(
        modifier = modifierWithBg
            .padding(5.dp, 0.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(1.dp, color = textColor, shape = CircleShape),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(10.dp, 5.dp),
            color = textColor
        )
    }
}