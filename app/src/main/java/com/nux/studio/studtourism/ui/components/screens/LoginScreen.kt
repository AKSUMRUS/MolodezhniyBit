package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary
import com.nux.studio.studtourism.ui.components.atoms.authforms.EmailInputField
import com.nux.studio.studtourism.ui.components.atoms.authforms.Label
import com.nux.studio.studtourism.ui.components.atoms.authforms.PasswordInputField
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH3
import com.nux.studio.studtourism.ui.theme.StudTourismTheme
import com.nux.studio.studtourism.ui.viewmodels.SignUpViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    to: String
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    val viewModel: SignUpViewModel = hiltViewModel()

    val currentRoute = navController.currentDestination?.route?: ""

    LaunchedEffect(viewModel.state.isSuccess) {
        if(viewModel.state.isSuccess == true) {
            if(to.isNullOrEmpty()) {
                navController.popBackStack()
            }
            else {
                navController.navigate(to) {
                    popUpTo(currentRoute) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .padding(15.dp)
        ) {
            HeadlineH3(
                text = "Готовься к путешествиям",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp),
                color = MaterialTheme.colors.background,
                textAlign = TextAlign.Center,
            )
            Label("E-mail")
            EmailInputField(value = email, onValueChange = { email = it })
            Label("Пароль")
            PasswordInputField(
                value = password,
                onValueChange = { password = it },
                isVisible = isVisible,
                onVisibleToggle = { isVisible = !isVisible }
            )
        }
        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(15.dp)
        ) {
            ButtonPrimary(
                text = "Войти",
                onClick = {
                    viewModel.login(
                        email = email,
                        password = password
                    )
                          },
                modifier = Modifier
                    .fillMaxWidth(),
                buttonColor = MaterialTheme.colors.primaryVariant,
            )
            ButtonPrimary(
                text = "Зарегестрироваться",
                onClick = {
                        navController.navigate("signUp?to=$to") {
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