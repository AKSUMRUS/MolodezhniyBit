package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.R

@Composable
fun Grabber(modifier: Modifier = Modifier, tint: Color = MaterialTheme.colors.onBackground) {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.grabber),
        contentDescription = "grabber",
        tint = tint,
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
    )
}