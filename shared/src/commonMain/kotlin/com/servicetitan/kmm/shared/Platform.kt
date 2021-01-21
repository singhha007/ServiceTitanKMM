package com.servicetitan.kmm.shared

expect class Platform() {
    val build: String
    val currentDate: String
}