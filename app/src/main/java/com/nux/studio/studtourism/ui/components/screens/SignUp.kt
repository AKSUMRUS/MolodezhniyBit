package com.nux.studio.studtourism.ui.components.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.ui.components.InputField
import com.nux.studio.studtourism.ui.components.atoms.ButtonPrimary
import com.nux.studio.studtourism.ui.theme.StudTourismTheme

@Preview
@Composable
private fun SingUpPreview() {
    StudTourismTheme {
        SignUp()
    }
}

@Composable
fun SignUp(
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val inputModifier = Modifier.padding(bottom = 10.dp)
    Column(
        modifier = Modifier.fillMaxHeight()
    ){
        InputField(text = name, placeholder = "Имя", modifier = inputModifier, onValueChange = {name = it})
        InputField(text = surname, placeholder = "Фамилия", modifier = inputModifier, onValueChange = {surname = it})
        InputField(text = middleName, placeholder = "Отчество", modifier = inputModifier, onValueChange = {middleName = it})
        InputField(text = email, placeholder = "Почта", modifier = inputModifier, onValueChange = {email = it})
        InputField(text = phone, placeholder = "Телефон", modifier = inputModifier, onValueChange = {phone = it})
        InputField(text = password, placeholder = "Пароль", modifier = inputModifier, onValueChange = {password = it})
        ButtonPrimary(text = "Создать", modifier = Modifier, onClick = {})
    }
}