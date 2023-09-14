package com.patronusgroup.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
)

val HeaderTextStyle = TextStyle(
    fontFamily = rubikFamily,
    color = BlackText,
    fontSize = 27.sp,
    lineHeight = 32.4.sp,
    fontWeight = FontWeight.Bold,
)

val FullNameTextStyle = TextStyle(
    fontFamily = rubikFamily,
    color = BlackText,
    fontWeight = FontWeight.Bold,
    fontSize = 17.sp,
    lineHeight = 20.4.sp,
)

val GenderTextStyle = TextStyle(
    fontFamily = rubikFamily,
    color = GreyText,
    fontSize = 17.sp,
    lineHeight = 20.4.sp,
)

val BodyTextStyle = TextStyle(
    fontFamily = rubikFamily,
    color = BlackBodyText,
    fontSize = 17.sp,
    lineHeight = 20.4.sp,
)

val ImagePlaceHolderTextStyle = TextStyle(
    fontFamily = rubikFamily,
    color = GreyText,
    fontWeight = FontWeight.Bold,
    fontSize = 22.sp,
    lineHeight = 26.4.sp,
)

val RedStickerTextStyle = TextStyle(
    fontFamily = rubikFamily,
    color = RedStickerText,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 15.6.sp,
)

val GreyStickerTextStyle = TextStyle(
    fontFamily = rubikFamily,
    color = GreyText,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 15.6.sp,
)

val Header2TextStyle = TextStyle(
    fontFamily = rubikFamily,
    color = BlackBodyText,
    fontWeight = FontWeight.Bold,
    fontSize = 15.sp,
    lineHeight = 18.sp,
)
