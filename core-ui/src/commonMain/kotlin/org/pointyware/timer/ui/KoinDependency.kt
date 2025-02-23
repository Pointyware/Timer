package org.pointyware.timer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin

@Composable
inline fun <reified T : Any> koinDependency(): T {
    val koin = remember { getKoin() }
    return remember { koin.get<T>() }
}
