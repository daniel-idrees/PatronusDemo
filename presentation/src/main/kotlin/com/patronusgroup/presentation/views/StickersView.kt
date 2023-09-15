package com.patronusgroup.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patronusgroup.domain.model.enums.Sticker
import com.patronusgroup.presentation.style.GreyStickerBackground
import com.patronusgroup.presentation.style.GreyStickerTextStyle
import com.patronusgroup.presentation.style.RedStickerBackground
import com.patronusgroup.presentation.style.RedStickerTextStyle

@Composable
internal fun StickersView(stickers: List<Sticker>) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        stickers.forEach {
            StickerCard(it)
        }
    }
}

@Composable
private fun StickerCard(sticker: Sticker) {
    val highlightColor = if (sticker == Sticker.FAM) {
        GreyStickerBackground
    } else {
        RedStickerBackground
    }

    val textStyle = if (sticker == Sticker.FAM) {
        GreyStickerTextStyle
    } else {
        RedStickerTextStyle
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = highlightColor,
        ),
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(
            text = sticker.value,
            modifier = Modifier.padding(
                horizontal = 4.dp,
                vertical = 1.5.dp,
            ),
            style = textStyle,
        )
    }
}
