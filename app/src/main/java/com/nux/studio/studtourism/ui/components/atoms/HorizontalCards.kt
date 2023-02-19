package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5

@Composable
fun HorizontalCardsContainer(
    modifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        content = content,
    )
}

@Composable
fun HorizontalCard(
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val configuration = LocalConfiguration.current
    Column(
        modifier = Modifier
            .width(configuration.screenWidthDp.dp - 25.dp)
            .fillMaxHeight()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = color)
            .padding(10.dp)
            .then(modifier)
    ) {
        content()
    }
}

@Composable
fun HorizontalCardDescription(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text, fontSize = 15.sp, modifier = Modifier.then(modifier),
    )
}