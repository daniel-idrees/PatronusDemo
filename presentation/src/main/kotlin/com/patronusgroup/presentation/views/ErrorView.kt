package com.patronusgroup.presentation.views

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patronusgroup.presentation.R
import com.patronusgroup.presentation.style.BodyTextStyle

@Composable
internal fun ErrorView(onRetryAction: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.error_text),
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
                text = stringResource(id = R.string.btn_try_again),
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
