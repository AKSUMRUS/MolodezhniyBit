package com.nux.studio.studtourism.ui.components.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.ui.components.atoms.texts.Subtitle2
import com.nux.studio.studtourism.ui.theme.StudTourismTheme
import com.nux.studio.studtourism.ui.viewmodels.ProfileViewModel
import java.util.*

@Preview
@Composable
private fun ProfileScreenPreview() {
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
    val studentRoleType = state.user?.studentRoleType ?: ""
    val universityName = state.user?.universityName ?: ""

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val month: Int = calendar.get(Calendar.MONTH)
    val year: Int = calendar.get(Calendar.YEAR)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, yearPicked: Int, monthRaw: Int, dayRaw: Int ->
            val dayPicked = String.format("%02d", dayRaw)
            val monthPicked = String.format("%02d", monthRaw + 1)
            val birthday = "$dayPicked.$monthPicked.$yearPicked"
            viewModel.setBirthDate(birthday)
        }, year, month, day
    )


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
                onValueChange = viewModel::setBirthDate,
                trailingIcon = {
                        Icon(
                            ImageVector.vectorResource(id = R.drawable.icon_calendar),
                            tint = Color.Black,
                            contentDescription = "calendar",
                            modifier = Modifier
                                .clickable {
                                    datePickerDialog.show()
                                }
                        )
                }
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
            InputFieldItem(
                title = "Роль",
                text = studentRoleType,
                onValueChange = viewModel::setStudentRoleType
            )
        }
        item {
            InputFieldItem(
                title = "ВУЗ",
                text = universityName,
                onValueChange = viewModel::setUniversity
            )
        }
        item {
            ButtonPrimary(
                text = "Сохранить изменения",
                textColor = MaterialTheme.colors.onSurface,
                buttonColor = MaterialTheme.colors.surface,
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
private fun InputFieldItem(
    title: String,
    text: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
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
        trailingIcon = trailingIcon
    )
}

@Composable
private fun TitleText(text: String, topPadding: Dp = 20.dp) {
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



