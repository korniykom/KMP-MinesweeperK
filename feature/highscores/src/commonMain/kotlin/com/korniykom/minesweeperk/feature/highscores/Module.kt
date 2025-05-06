package com.korniykom.minesweeperk.feature.highscores

import com.korniykom.minesweeperk.domain.game.domainGameModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val highscoresModule = module {
    viewModelOf(::HighscoresViewModel)
    includes(domainGameModule)
}