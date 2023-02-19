package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OurRadioButton(text: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val onColor = if (selected) MaterialTheme.colors.onSurface else MaterialTheme.colors.onBackground
    val color = if (selected) MaterialTheme.colors.surface else MaterialTheme.colors.background
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = onColor
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(10.dp),
            color = onColor
        )
    }
}