package com.korniykom.minesweeperk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform