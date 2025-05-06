package com.korniykom.minesweeperk.domain.game

import com.korniykom.minesweeperk.data.game.dataGameModule
import com.korniykom.minesweeperk.data.settings.dataSettingsModule
import org.koin.dsl.module

val domainGameModule = module { includes(dataSettingsModule, dataGameModule) }