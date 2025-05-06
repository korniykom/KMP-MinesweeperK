package com.korniykom.minesweeperk.domain.settings

import com.korniykom.minesweeperk.data.settings.dataSettingsModule
import org.koin.dsl.module

val domainSettingsModule = module {
    includes(dataSettingsModule)
}