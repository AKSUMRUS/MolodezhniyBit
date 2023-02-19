package com.nux.studio.studtourism.ui.components.atoms

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun ImagesCarousel(photos: Collection<String>) {

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxWidth()
    ) {
        LazyRow(
            Modifier.clip(
                RoundedCornerShape(
                    bottomEnd = 30.dp,
                    bottomStart = 30.dp
                )
            ),
            state = listState,
        ) {
            photos.forEach { photoUrl ->
                item {
                    AsyncImage(
                        model = photoUrl,
                        contentDescription = "Фото общежития",
                        modifier = Modifier
                            .width(LocalConfiguration.current.screenWidthDp.dp)
                            .height(300.dp)
                            .padding(0.dp),
                        contentScale = ContentScale.FillWidth,
                    )
                }
            }
            item {
                Box(modifier = Modifier.width(10.dp))
            }
        }
        if (photos.size > 1) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 30.dp)
            ) {
                for (i in photos.indices) {
                    var color = MaterialTheme.colors.background;
                    if (listState.firstVisibleItemIndex != i) {
                        color = color.copy(0.3f)
                    }
                    Button(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(20.dp),
                        onClick = {
                            coroutineScope.launch {
                                Log.d("FirstVisible", "${listState.firstVisibleItemIndex}")
                                Log.d("TotalItemsCount", "${listState.layoutInfo.totalItemsCount}")
                                Log.d(
                                    "Offset", "${
                                        listState.firstVisibleItemScrollOffset
                                    }"
                                );
                                listState.animateScrollToItem(i)
                            }
                        },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(backgroundColor = color)
                    ) {
                    }
                }
            }
        }
    }
}