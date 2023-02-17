package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.nux.studio.studtourism.ui.components.InputField
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary

@Composable
fun SignUp(
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxHeight()
    ){
        InputField(text = name, placeholder = "Имя", modifier = Modifier, onValueChange = {name = it})
        InputField(text = surname, placeholder = "Фамилия", modifier = Modifier, onValueChange = {surname = it})
        InputField(text = middleName, placeholder = "Отчество", modifier = Modifier, onValueChange = {middleName = it})
        InputField(text = email, placeholder = "Почта", modifier = Modifier, onValueChange = {email = it})
        InputField(text = phone, placeholder = "Телефон", modifier = Modifier, onValueChange = {phone = it})
        InputField(text = password, placeholder = "Пароль", modifier = Modifier, onValueChange = {password = it})
        ButtonPrimary(text = "Создать", modifier = Modifier, onClick = {})
    }
}