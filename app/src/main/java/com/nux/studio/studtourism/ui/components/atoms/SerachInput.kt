package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nux.studio.studtourism.R

@Composable
fun SearchInput(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(all = 4.dp)
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, CircleShape),
        shape = CircleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onPrimary,
            disabledTextColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 15.sp,
        ),
        onValueChange = onValueChange,
        maxLines = 1,
        leadingIcon = {
            Icon(
                ImageVector.vectorResource(id = R.drawable.icon_search),
                contentDescription = "checked",
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .padding(15.dp)
                    .background(Color.Transparent)
                    .clip(RectangleShape)
            )
        }
    )
}