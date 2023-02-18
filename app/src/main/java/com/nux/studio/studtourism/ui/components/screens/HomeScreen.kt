package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.ui.components.molecules.CardDormitory

@Composable
fun HomeScreen(){

    var dormitories = arrayListOf<String>("Test1", "Test2", "Test3", "Test4")

    LazyColumn(
        contentPadding = PaddingValues(top = 0.dp, start = 20.dp, end = 20.dp),
        modifier = Modifier,
    ) {
        items(dormitories) { dormitory ->
            CardDormitory(
                title = dormitory,
                link_icon = "https://stud-files.sabir.pro/files/RSiDUfWDkz-8ce9a3769c9f55b1aac0501475e37e0f8ba76ca1dbf5629eb1f142a69f1d0786.jpg",
                cost = "500 рублей",
                time = "17 марта",
            )
        }
    }

}