package com.nux.studio.studtourism.ui.components.screens

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
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

    LazyColumn(
        contentPadding = PaddingValues(top = 0.dp, start = 20.dp, end = 20.dp),
        modifier = modifier,
    ) {
        items(events) { event ->
            Text(
                text = event.details.name.toString(),
                color = MaterialTheme.colors.error
            )
        }
    }

}