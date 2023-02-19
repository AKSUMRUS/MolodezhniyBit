package com.nux.studio.studtourism.ui.components.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.molecules.CardEvent
import com.nux.studio.studtourism.ui.components.molecules.LoadingViewCenter
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navController: NavController,
    ) {
    val events = viewModel.state.eventsList
    val isLoading = viewModel.state.isLoading

    val filters = viewModel.filterState

    Log.e("LOGGGG", viewModel.toString())

    LaunchedEffect(true) {
        viewModel.getEvents()
    }

    Box(
        modifier = modifier
    ) {
        if (isLoading) {
            LoadingViewCenter()
        } else {
            val list = viewModel.state.eventsList
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(top = 0.dp, start = 0.dp, end = 0.dp),
                modifier = Modifier.background(MaterialTheme.colors.background),
            ) {
                itemsIndexed(list) { index, event ->
                    CardEvent(
                        event = event,
                        height = 240,
                        onClick = {
                            navController.navigate(
                                "event?index=$index"
                            )
                        }
                    )
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 24.dp),
            backgroundColor = MaterialTheme.colors.surface,
            onClick = {
                //
            }
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.filters),
                contentDescription = "Filter's button"
            )
        }
    }
}