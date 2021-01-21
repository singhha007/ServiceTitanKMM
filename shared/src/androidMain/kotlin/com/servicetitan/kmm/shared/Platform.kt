package com.servicetitan.kmm.shared

import android.os.Build
import java.time.LocalDateTime

actual class Platform actual constructor() {
    actual val build: String = "Android ${Build.VERSION.SDK_INT}"
    actual val currentDate: String = LocalDateTime.now().toString()
}