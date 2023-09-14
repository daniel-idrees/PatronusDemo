package com.patronusgroup.ui.screens.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patronusgroup.ui.theme.BodyTextStyle

@Composable
fun ErrorView(onRetryAction: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Something went wrong ...",
            style = BodyTextStyle,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 60.dp)
                .fillMaxWidth(),
            onClick = onRetryAction,
        ) {
            Text(
                text = "Try Again",
                style = BodyTextStyle,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorViewPreview() {
    ErrorView {}
}
