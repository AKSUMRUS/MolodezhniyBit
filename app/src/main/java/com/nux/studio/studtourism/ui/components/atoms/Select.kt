package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.nux.studio.studtourism.R

@Preview
@Composable
fun SelectPreview() {
    val items = listOf("123", "one", "two", "one two")
    var selected by remember { mutableStateOf("") }
    Select(items, value = selected, onValueChange = { selected = it })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Select(
    items: List<String>,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
) {
    var expanded by remember { mutableStateOf(false) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    val keyboardController = LocalSoftwareKeyboardController.current

//    Column(modifier = Modifier.fillMaxWidth().then(modifier)) {
    InputField(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                //This value is used to assign to the DropDown the same width
                textfieldSize = coordinates.size.toSize()
            }
            .then(modifier),
        placeholder = placeholder,
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }),
        onValueChange = { onValueChange(it) },
        trailingIcon = {
            Icon(icon, "contentDescription",
                Modifier.clickable { expanded = !expanded },
                tint = MaterialTheme.colors.onPrimary
            )
        }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
            .background(MaterialTheme.colors.background)
    ) {

        items.forEach { item ->
            if (item.contains(value, true)) {
                DropdownMenuItem(
                    onClick = { onValueChange(item); expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = item, color = MaterialTheme.colors.onBackground)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                        ) {
                            if (value == item) {

                                Image(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_check),
                                    contentDescription = "checked",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .fillMaxWidth()
                                        .align(Alignment.CenterEnd)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
//    }
}