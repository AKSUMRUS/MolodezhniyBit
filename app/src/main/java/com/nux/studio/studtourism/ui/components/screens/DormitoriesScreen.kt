package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import com.nux.studio.studtourism.ui.viewmodels.SignUpViewModel

@Composable
fun DormitoriesScreen(
    navController: NavController,
){

    val mainViewModel: MainViewModel = hiltViewModel()
    var height = (200..400).random()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 10.dp, start = 0.dp, end = 0.dp),
        modifier = Modifier,
    ) {
        itemsIndexed(mainViewModel.state.dormitoriesList) { index,dormitory ->
            if(index % 2==0){
                height = (200..400).random()
            }

            CardDormitory(
                dormitory = dormitory.details!!,
                height = height,
                navController = navController,
            )
        }
    }

}