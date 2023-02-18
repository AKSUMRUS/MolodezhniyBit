package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonPrimary(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonColor: Color = MaterialTheme.colors.primary,
    textColor: Color = MaterialTheme.colors.onPrimary,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .clip(CircleShape)
            .then(modifier),

        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
        ),
        shape = CircleShape,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(10.dp),
            color = textColor,
        )
    }
}