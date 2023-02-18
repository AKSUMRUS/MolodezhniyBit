package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary
import com.nux.studio.studtourism.ui.components.atoms.ButtonSecondary
import com.nux.studio.studtourism.ui.theme.StudTourismTheme
import com.nux.studio.studtourism.ui.viewmodels.SignUpViewModel
import kotlin.concurrent.fixedRateTimer

@Composable
fun SignUp(
    navController: NavController
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.state.isSuccess) {
        if(viewModel.state.isSuccess == true) {
            navController.navigate("profile") {
                popUpTo("signUp") {
                    inclusive = true
                }
            }
        }
    }

    val inputModifier = Modifier.padding(bottom = 10.dp)
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        InputField(
            text = name,
            placeholder = "Имя",
            modifier = inputModifier,
            onValueChange = { name = it })
        InputField(
            text = surname,
            placeholder = "Фамилия",
            modifier = inputModifier,
            onValueChange = { surname = it })
        InputField(
            text = middleName,
            placeholder = "Отчество",
            modifier = inputModifier,
            onValueChange = { middleName = it })
        InputField(
            text = email,
            placeholder = "Почта",
            modifier = inputModifier,
            onValueChange = { email = it })
        InputField(
            text = phone,
            placeholder = "Телефон",
            modifier = inputModifier,
            onValueChange = { phone = it })
        InputField(
            text = password,
            placeholder = "Пароль",
            modifier = inputModifier,
            onValueChange = { password = it })
        ButtonPrimary(text = "Создать", modifier = Modifier, onClick = {
            viewModel.signUp(
                email = email,
                password = password,
                fistName = name,
                lastName = surname,
                middleName = middleName,
                phone = phone,
            )
        })
        ButtonSecondary(
            text = "Уже есть аккаунт? Войти",
            modifier = Modifier,
            onClick = {
                navController.navigate("login") {
                    popUpTo("signUp") {
                        inclusive = true
                    }
                }
            }
        )
    }
}