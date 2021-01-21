package com.servicetitan.kmm.shared

import platform.Foundation.NSDate
import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val build: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val currentDate: String = NSDate().toString()
}