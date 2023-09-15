package com.patronusgroup.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.patronusgroup.presentation.style.GreyBackground
import com.patronusgroup.presentation.style.ImagePlaceHolderTextStyle

private val imageSize = 48.dp

@Composable
internal fun DisplayImage(imageUrl: String?, nameInitials: String) {
    val hasError = remember { mutableStateOf(false) }

    if (!hasError.value) {
        DeviceHolderImage(imageUrl, hasError)
    } else {
        FallbackImagePlaceholder(nameInitials)
    }
}

@Composable
private fun DeviceHolderImage(url: String?, hasError: MutableState<Boolean>) {
    AsyncImage(
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape),
        onError = {
            hasError.value = true
        },
    )
}

@Composable
private fun FallbackImagePlaceholder(nameInitials: String) {
    Box(
        modifier = Modifier
            .size(imageSize)
            .background(
                color = GreyBackground,
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = nameInitials,
            style = ImagePlaceHolderTextStyle,
        )
    }
}
