package com.korniykom.minesweeperk.feature.settings

import com.korniykom.minesweeperk.domain.settings.domainSettingsModule
import org.koin.dsl.module

val settingsModule = module {
    includes(domainSettingsModule)
}