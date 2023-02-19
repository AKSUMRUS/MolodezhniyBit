    package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

    /**
 * Draw circle image by url
 */
@Composable
fun CircleAsyncImage(
    url: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = url,
        contentDescription = description,
        modifier = modifier.then(
            Modifier
                .size(40.dp)
                .clip(CircleShape)
        ),
        contentScale = ContentScale.Crop
    )
}