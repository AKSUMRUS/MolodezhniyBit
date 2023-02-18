package com.nux.studio.studtourism.ui.components.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory
import com.nux.studio.studtourism.ui.components.molecules.LoadingViewCenter
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun DormitoriesScreen(
    navController: NavController,
    viewModel: MainViewModel
){
    val isLoading = viewModel.state.isLoading

    if(isLoading) {
        LoadingViewCenter()
    } else {
        LazyColumn(
            contentPadding = PaddingValues(top = 0.dp, start = 20.dp, end = 20.dp),
            modifier = Modifier.background(MaterialTheme.colors.primary),
        ) {
            itemsIndexed(viewModel.state.dormitoriesList) { index, dormitory ->
                CardDormitory(
                    dormitory = dormitory.details!!,
                    onClick = {
                        Log.e("LOGGGG", viewModel.toString() )
                        navController.navigate(
                            "dormitory?index=$index"
                        )
                    }
                )
            }
        }
    }
}