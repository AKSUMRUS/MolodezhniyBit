package com.nux.studio.studtourism.ui.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import coil.compose.AsyncImage
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH2

@Composable
fun CardDormitory(
    title: String = "Dormitory",
    link_icon: String?,
    cost: String,
    time: String,
){

    Column(
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {
        AsyncImage(model = link_icon, contentDescription = "")
        
        Text(text = title)

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon),
                contentDescription = "",
                modifier = Modifier,
            )

            HeadlineH2(text = cost, modifier = Modifier)
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon),
                contentDescription = "",
                modifier = Modifier,
            )

            HeadlineH2(text = cost, modifier = Modifier)
        }

    }

}