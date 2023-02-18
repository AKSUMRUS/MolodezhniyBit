package com.nux.studio.studtourism.ui.components.screens

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun EventsScreen(
    modifier: Modifier = Modifier
){

    val viewModel: MainViewModel = hiltViewModel<MainViewModel>()
    val events = viewModel.state.eventsList

    LaunchedEffect(true) {
        viewModel.getEvents()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 80.dp, start = 0.dp, end = 0.dp),
        modifier = Modifier,
    ) {
        items(events) { event ->
            Text(
                text = event.details.name.toString(),
                color = MaterialTheme.colors.error
            )
        }
    }

}