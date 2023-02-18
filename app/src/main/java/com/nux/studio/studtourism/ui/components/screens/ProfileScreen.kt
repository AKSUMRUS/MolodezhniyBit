package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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

    val lastName = state.user?.lastName ?: ""
    val firstName = state.user?.firstName ?: ""
    val middleName = state.user?.middleName ?: ""
    val birthDate = state.user?.birthday ?: ""
    val gender = state.user?.gender ?: ""
    val departureCity = state.user?.departureCity ?: ""
    val email = state.user?.email ?: ""
    val phone = state.user?.phone ?: ""
    val userRole = state.user
    
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
            TitleText("Основная информация")
        }
        //Фамилия
        item {
            InputFieldItem(
                title = "Фамилия",
                text = lastName,
                onValueChange = viewModel::setLastName
            )
        }
        //Имя
        item {
            InputFieldItem(
                title = "Имя",
                text = firstName,
                onValueChange = viewModel::setFirstName
            )
        }
        //Отчество
        item {
            InputFieldItem(
                title = "Отчество",
                text = middleName,
                onValueChange = viewModel::setMiddleName
            )
        }
        // Дата рождения
        item {
            InputFieldItem(
                title = "Дата рождения",
                text = birthDate,
                onValueChange = viewModel::setBirthDate
            )
        }
        // Пол
        item {
            InputFieldItem(
                title = "Пол",
                text = gender,
                onValueChange = viewModel::setGender
            )
        }
        // Город отправления
        item {
            InputFieldItem(
                title = "Город отправления",
                text = departureCity,
                onValueChange = viewModel::setDepartureCity
            )
        }
        item { 
            TitleText(text = "Контакты", topPadding = 48.dp)
        }
        item { 
            InputFieldItem(
                title = "E-mail",
                text = email,
                onValueChange = viewModel::setEmail
            )
        }
        item { 
            InputFieldItem(
                title = "Телефон",
                text = phone,
                onValueChange = viewModel::setPhone
            )
        }
        item { 
            TitleText(text = "Данные о студенте", topPadding = 48.dp)
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
                        departureCity = departureCity,
                        gender = gender
                    )
                },
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        bottom = 24.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun InputFieldItem(title: String, text: String, onValueChange: (String) -> Unit) {
    Subtitle2(
        text = title,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 4.dp
        )
    )
    InputField(
        text = text,
        placeholder = title,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
            )
            .background(Color(0xF3F8FCFF)),
    )
}

@Composable
fun TitleText(text: String, topPadding: Dp = 20.dp) {
    HeadlineH5(
        text = text,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            top = topPadding,
            start = 16.dp,
            end = 16.dp,
            bottom = 8.dp
        )
    )
}


