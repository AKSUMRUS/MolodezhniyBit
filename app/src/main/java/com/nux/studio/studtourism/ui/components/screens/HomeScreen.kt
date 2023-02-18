package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun HomeScreen(){

    val viewModel: MainViewModel = hiltViewModel<MainViewModel>()
    val dormitories = viewModel.state.dormitoriesList

    LazyColumn(
        contentPadding = PaddingValues(top = 0.dp, start = 20.dp, end = 20.dp),
        modifier = Modifier,
    ) {
        items(dormitories) { dormitory ->
            CardDormitory(
                title = dormitory.details?.mainInfo!!.name,
                link_icon = dormitory.details.mainInfo.photos?.get(0),
                cost = "500 рублей",
                time = "17 марта",
            )
        }
    }

}