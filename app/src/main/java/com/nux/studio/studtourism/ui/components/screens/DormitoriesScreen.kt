package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import com.nux.studio.studtourism.ui.viewmodels.SignUpViewModel

@Composable
fun DormitoriesScreen(
){

    val mainViewModel: MainViewModel = hiltViewModel()

    LazyColumn(
        contentPadding = PaddingValues(top = 0.dp, start = 20.dp, end = 20.dp),
        modifier = Modifier.background(MaterialTheme.colors.primary),
    ) {
        items(mainViewModel.state.dormitoriesList) { dormitory ->
            CardDormitory(
                dormitory = dormitory.details!!,
            )
        }
    }

}