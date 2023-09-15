package com.patronusgroup.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patronusgroup.core.util.NetworkMonitor
import com.patronusgroup.presentation.style.PatronusDemoTheme
import com.patronusgroup.ui.nav.MainNavHost

@Composable
fun PatronusDemoApp(networkMonitor: NetworkMonitor) {
    val isNetworkAvailable = networkMonitor.isConnected.collectAsStateWithLifecycle(true)

    PatronusDemoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            MainNavHost(isNetworkAvailable.value)
        }
    }
}
