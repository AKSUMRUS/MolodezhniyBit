package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
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
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var departure by remember { mutableStateOf("") }


    LazyColumn(
        modifier = Modifier.padding(
            bottom = 24.dp
        )
    ) {
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
                placeholder = "Фамилия",
                onValueChange = { lastName = it },
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .background(Color(0xF3F8FCFF)),
            )
        }
        //Имя
        item {
            Subtitle2(
                text = "Имя",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            )
            InputField(
                text = firstName,
                placeholder = "Имя",
                onValueChange = { firstName = it },
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .background(Color(0xF3F8FCFF)),
            )
        }
        //Отчество
        item {
            Subtitle2(
                text = "Отчество",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            )
            InputField(
                text = middleName,
                placeholder = "Отчество",
                onValueChange = { middleName = it },
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .background(Color(0xF3F8FCFF)),
            )
        }
        // Дата рождения
        item {
            Subtitle2(
                text = "Дата рождения",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            )
            InputField(
                text = birthDate,
                placeholder = "Дата рождения",
                onValueChange = { birthDate = it },
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .background(Color(0xF3F8FCFF)),
            )
        }
        // Пол
        item {
            Subtitle2(
                text = "Пол",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            )
            InputField(
                text = gender,
                placeholder = "Пол",
                onValueChange = { gender = it },
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .background(Color(0xF3F8FCFF)),
            )
        }
        // Город отправления
        item {
            Subtitle2(
                text = "Город отправления",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            )
            InputField(
                text = departure,
                placeholder = "Город отправления",
                onValueChange = { departure = it },
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                    )
                    .background(Color(0xF3F8FCFF)),
            )
        }
        item {
            ButtonPrimary(
                text = "Сохранить изменения",
                onClick = {
                    viewModel.editProfile(
                        lastName = lastName,
                        firstName = firstName,
                        middleName = middleName,
                        birthday = birthDate,
                        departureCity = departure,
                        gender = gender
                    )
                },
                modifier = Modifier.padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp,
                ).fillMaxWidth()
            )

        }
    }
}


