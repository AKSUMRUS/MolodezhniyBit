package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary

@Composable
fun SignIn(
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxHeight()
    ){
        InputField(text = email, placeholder = "Почта", modifier = Modifier, onValueChange = {email = it})
        InputField(text = password, placeholder = "Пароль", modifier = Modifier, onValueChange = {password = it})
        ButtonPrimary(text = "Создать", modifier = Modifier, onClick = {})
    }
}