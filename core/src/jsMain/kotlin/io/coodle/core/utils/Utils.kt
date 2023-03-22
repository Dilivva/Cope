package io.coodle.core.utils

import kotlinx.datetime.Clock

actual fun getCurrentTimeMls(): Long {
    return Clock.System.now().toEpochMilliseconds()
}