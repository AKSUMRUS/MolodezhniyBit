package com.nux.studio.studtourism.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nux.studio.studtourism.R

var fontFamily = FontFamily(
    Font(R.font.objectsans_regular, FontWeight.Normal),
    Font(R.font.objectsans_bold, FontWeight.Bold)
)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp,
    ),

    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.0.sp,
        fontFamily = fontFamily,
        lineHeight = 16.sp,

    )



    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)