package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH3
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH6
import com.nux.studio.studtourism.ui.components.atoms.texts.Subtitle2
import com.nux.studio.studtourism.ui.theme.StudTourismTheme
import com.nux.studio.studtourism.ui.viewmodels.ProfileViewModel

@Preview
@Composable
fun ProfileScreenPreview() {
    StudTourismTheme {
        ProfileScreen()
    }
}


@Composable
fun ProfileScreen() {
    val viewModel: ProfileViewModel = hiltViewModel()
    val state = viewModel.profileState

    var lastName by remember { mutableStateOf("") }

    LazyColumn {
        item {
            val photo = state.user?.avatar
            AsyncImage(
                model = photo,
                contentDescription = "Фото профиля",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp,
                        )
                    )
                    .height(445.dp)
                    .padding(0.dp),
                contentScale = ContentScale.Crop
            )
        }
        item {
            HeadlineH5(
                text = "Основная информация",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            )
        }
        //Фамилия
        item {
            Subtitle2(
                text = "Фамилия",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            )
            InputField(
                text = lastName,
                placeholder = "Имя",
                onValueChange = { lastName = it },
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                ).background(Color(0xF3F8FCFF)),

            )
        }

    }
}


