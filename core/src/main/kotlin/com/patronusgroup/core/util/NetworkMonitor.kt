package com.patronusgroup.core.util

import kotlinx.coroutines.flow.Flow

/**
 * https://github.com/android/nowinandroid/blob/151f877bbe6546c78f8f03f16e56564e285fb508/core/data/src/main/java/com/google/samples/apps/nowinandroid/core/data/util/NetworkMonitor.kt
 */
interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}