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
import androidx.compose.ui.unit.sp

enum class PillVariant {
    PRIMARY,
    BACKGROUND,
    OUTLINE,
}

@Composable
fun Pill(
    text: String,
    modifier: Modifier = Modifier,
    variant: PillVariant = PillVariant.BACKGROUND,
    textColor: Color? = null,
) {
    val bgColor = when (variant) {
        PillVariant.PRIMARY -> MaterialTheme.colors.primary
        PillVariant.BACKGROUND -> MaterialTheme.colors.background
        PillVariant.OUTLINE -> Color.Transparent
    }
    val textColorCalculated = textColor?: when (variant) {
        PillVariant.PRIMARY -> MaterialTheme.colors.onPrimary
        PillVariant.BACKGROUND -> MaterialTheme.colors.onBackground
        PillVariant.OUTLINE -> MaterialTheme.colors.onBackground
    }
    Box(
        modifier = Modifier
            .padding(5.dp, 5.dp)
            .clip(CircleShape)
            .border(1.dp, color = textColorCalculated, shape = CircleShape)
            .background(bgColor)
            .then(modifier)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(10.dp, 5.dp),
            color = textColorCalculated,
            fontSize = 15.sp,
        )
    }
}