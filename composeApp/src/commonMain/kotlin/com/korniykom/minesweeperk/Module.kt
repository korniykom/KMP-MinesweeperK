package com.korniykom.minesweeperk

import com.korniykom.minesweeperk.feature.play.playModule
import com.korniykom.minesweeperk.feature.settings.settingsModule
import com.korniykom.minesweeperk.feature.highscores.highscoresModule
import com.korniykom.minesweeperk.feature.menu.menuModule
import org.koin.dsl.module

val appModule = module {
    includes(
        highscoresModule, settingsModule, playModule, menuModule
    )
}