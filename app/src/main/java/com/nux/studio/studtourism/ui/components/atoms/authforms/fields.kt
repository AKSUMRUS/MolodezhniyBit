package com.nux.studio.studtourism.ui.components.atoms.authforms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.ui.components.atoms.InputField
import com.nux.studio.studtourism.ui.components.atoms.texts.Subtitle1


@Composable
fun Label(text: String) {
    Subtitle1(
        text = text, color = MaterialTheme.colors.background,
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    )
}

@Composable
fun EmailInputField(value: String, onValueChange: (String) -> Unit) {
    InputField(text = value, onValueChange = onValueChange, keyboardType = KeyboardType.Email)
}

@Composable
fun PasswordInputField(
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