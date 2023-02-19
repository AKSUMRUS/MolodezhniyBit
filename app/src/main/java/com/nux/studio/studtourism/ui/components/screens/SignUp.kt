package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.components.atoms.authforms.EmailInputField
import com.nux.studio.studtourism.ui.components.atoms.authforms.Label
import com.nux.studio.studtourism.ui.components.atoms.authforms.PasswordInputField
import com.nux.studio.studtourism.ui.viewmodels.SignUpViewModel

@Composable
fun SignUp(
    navController: NavController,
    to: String
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    val currentRoute = navController.currentDestination?.route?: ""

    LaunchedEffect(viewModel.state.isSuccess) {
        if(viewModel.state.isSuccess == true) {
            if(to.isNullOrEmpty()) {
                navController.popBackStack()
            } else {
                navController.navigate(to) {
                    popUpTo(currentRoute) {
                        inclusive = true
                    }
                }
            }
        }
    }

    val inputModifier = Modifier.padding(bottom = 5.dp)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .padding(15.dp)
        ) {
            item {
                Label("Имя")
                InputField(
                    text = name,
                    modifier = inputModifier,
                    onValueChange = { name = it })
                Label("Фамилия")
                InputField(
                    text = surname,
                    modifier = inputModifier,
                    onValueChange = { surname = it })
                Label("Отчество")
                InputField(
                    text = middleName,
                    modifier = inputModifier,
                    onValueChange = { middleName = it })
                Label("E-mail")
                EmailInputField(value = email, onValueChange = { email = it })
                Label("Телефон")
                InputField(
                    text = phone,
                    modifier = inputModifier,
                    onValueChange = { phone = it })
                Label("Пароль")
                PasswordInputField(
                    value = password,
                    onValueChange = { password = it },
                    isVisible = isVisible,
                    onVisibleToggle = { isVisible = !isVisible }
                )
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .padding(15.dp)
                ) {
                    ButtonPrimary(
                        text = "Создать",
                        onClick = {
                            viewModel.signUp(
                                email = email,
                                password = password,
                                fistName = name,
                                lastName = surname,
                                middleName = middleName,
                                phone = phone,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        buttonColor = MaterialTheme.colors.primaryVariant,
                    )
                    ButtonPrimary(
                        text = "Уже есть аккаунт? Войти",
                        onClick = {
                            navController.navigate("login?to=$to") {
                                popUpTo(currentRoute) {
                                    inclusive = true
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        buttonColor = Color.Transparent,
                        textColor = MaterialTheme.colors.background,
                    )
                }
            }
        }
    }
}