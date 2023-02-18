package com.nux.studio.studtourism.ui.components.screens

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
import com.nux.studio.studtourism.ui.components.molecules.LoadingViewCenter
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun LabsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){

    val labs = viewModel.state.labsList
    val isLoading = viewModel.state.isLoading

    LaunchedEffect(true) {
        viewModel.getLabs()
    }

    if(isLoading) {
        LoadingViewCenter()
    } else {
        LazyColumn(
            contentPadding = PaddingValues(top = 0.dp, start = 20.dp, end = 20.dp),
            modifier = modifier,
        ) {
            items(labs) { lab ->
                Text(
                    text = lab.details.name ?: "",
                    color = MaterialTheme.colors.error
                )
            }
        }
    }

}