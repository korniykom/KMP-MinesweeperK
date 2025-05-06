package com.korniykom.minesweeperk.feature.play

import com.korniykom.minesweeperk.domain.game.domainGameModule
import org.koin.dsl.module

val playModule = module {  includes(domainGameModule)}