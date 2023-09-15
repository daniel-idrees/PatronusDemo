package com.patronusgroup.presentation.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun SpacerM() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
internal fun SpacerS() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
internal fun SpacerXS() {
    Spacer(modifier = Modifier.height(5.dp))
}
