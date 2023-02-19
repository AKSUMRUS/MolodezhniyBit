package com.nux.studio.studtourism.ui.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nux.studio.studtourism.R

@Composable
fun BackButton(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(25.dp)
            .clickable {
                navController.popBackStack()
            }
            .background(MaterialTheme.colors.surface.copy(alpha = 0.2f))
            .clip(RoundedCornerShape(30.dp))
            .then(modifier)
    ) {
        Icon(
            ImageVector.vectorResource(id = R.drawable.ic_back),
            contentDescription = "go back",
            tint = Color.White,
            modifier = Modifier
                .padding(
                    top = 4.dp,
                    end = 12.dp,
                    start = 10.dp,
                    bottom = 4.dp
                )
        )
        Text(
            text = "Назад",
            color = Color.White,
            modifier = Modifier.padding(end = 10.dp)
        )
    }
}