package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH3
import com.nux.studio.studtourism.ui.components.atoms.texts.Subtitle1
import com.nux.studio.studtourism.ui.theme.StudTourismTheme

@Preview
@Composable
fun PreviewRegistrationScreen() {
    StudTourismTheme {
        RegistrationScreen()
    }
}

@Composable
private fun Label(text: String) {
    Subtitle1(
        text = text, color = MaterialTheme.colors.background,
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    )
}

@Composable
private fun EmailInputField(value: String, onValueChange: (String) -> Unit) {
    InputField(text = value, onValueChange = onValueChange, keyboardType = KeyboardType.Email)
}

@Composable
private fun PasswordInputField(
    value: String, onValueChange: (String) -> Unit,
    isVisible: Boolean, onVisibleToggle: () -> Unit
) {
    InputField(
        text = value,
        onValueChange = onValueChange,
        keyboardType = KeyboardType.Password,
        trailingIcon = {
            Button(onClick = onVisibleToggle) {
                Icon(
                    ImageVector.vectorResource(
                        id = if (isVisible) {
                            R.drawable.icon_eye_open
                        } else {
                            R.drawable.icon_eye_closed
                        }
                    ),
                    contentDescription = "toggle password visibility",
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .padding(15.dp)
                        .background(Color.Transparent)
                        .clip(RectangleShape)
                )
            }
        },
        visualTransformation = if (isVisible) {
            null
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun RegistrationScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }
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
                text = "Регистрация",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                color = MaterialTheme.colors.background,
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
            Label("Повторите пароль")
            PasswordInputField(
                value = passwordRepeat,
                onValueChange = { passwordRepeat = it },
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
                text = "Зарегистрироваться",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(),
                buttonColor = MaterialTheme.colors.primaryVariant,
            )
            ButtonPrimary(
                text = "Войти",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(),
                buttonColor = Color.Transparent,
                textColor = MaterialTheme.colors.background,
            )
        }
    }
}