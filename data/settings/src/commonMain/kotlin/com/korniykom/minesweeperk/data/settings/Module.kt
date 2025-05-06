package com.korniykom.minesweeperk.data.settings

import com.korniykom.minesweeperk.data.core.dataCoreModule
import org.koin.dsl.module

val dataSettingsModule = module {
    includes(dataCoreModule)
}