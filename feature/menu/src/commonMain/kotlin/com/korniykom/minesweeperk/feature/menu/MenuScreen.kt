package com.korniykom.minesweeperk.feature.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.minesweeperk.ui.core.LocalDimensions
import com.korniykom.minesweeperk.ui.core.LocalPadding

@Composable
internal fun MenuScreen(
    goToPlay: () -> Unit,
    goToHighscores: () -> Unit,
    goToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        val buttonModifier =
            Modifier.padding(horizontal = LocalPadding.current.normal).widthIn(max = LocalDimensions.current.maxWidthSmall).fillMaxWidth()
        MenuButton(onClick = goToPlay, modifier = buttonModifier, text = "Play")
        MenuButton(onClick = goToHighscores, modifier = buttonModifier, text = "Highscrores")
        MenuButton(onClick = goToSettings, modifier = buttonModifier, text = "Settings")
    }
}