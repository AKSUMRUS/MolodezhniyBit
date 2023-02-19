package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    textStyle: TextStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.onBackground),
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation? = null,
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ).copy(imeAction = imeAction, capitalization = capitalization),
        textStyle = textStyle,
        keyboardActions = keyboardActions,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                MaterialTheme.colors.primary
            )
            .then(modifier),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onPrimary,
            placeholderColor = MaterialTheme.colors.secondaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = Color.DarkGray,
            disabledTextColor = MaterialTheme.colors.onPrimary,
        ),
        enabled = enabled,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation?: VisualTransformation.None,
    )
}

@Composable
fun InputField2(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    textStyle: TextStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.onBackground),
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation? = null,
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ).copy(imeAction = imeAction, capitalization = capitalization),
        textStyle = textStyle,
        keyboardActions = keyboardActions,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                MaterialTheme.colors.primary
            )
            .then(modifier),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onPrimary,
            placeholderColor = MaterialTheme.colors.secondaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = Color.DarkGray,
            disabledTextColor = MaterialTheme.colors.onPrimary,
        ),
        enabled = enabled,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation?: VisualTransformation.None,
    )
}

