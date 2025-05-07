package com.korniykom.minesweeperk.feature.play

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import minesweeperk.feature.play.generated.resources.Res
import minesweeperk.feature.play.generated.resources.mine
import org.jetbrains.compose.resources.painterResource

@Composable
fun MinesweeperBoard(
    tileStates: List<List<TileState>>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
) {
    if (tileStates.isNotEmpty()) {
        val boardHeight = remember { tileStates.first().size }
        val boardWidth = remember { tileStates.size }
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            val requiredRation = boardWidth / boardHeight.toFloat()
            val currentRation = maxWidth / maxHeight
            val tileLength = if (requiredRation > currentRation) {
                maxWidth / boardWidth
            } else {
                maxHeight / boardHeight
            }
            val sizeAdjustedTextStyle = textStyle.copy(
                fontSize = tileLength.value.sp * 0.73f
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.width(tileLength * boardWidth).height(tileLength * boardHeight)
            ) {
                for(x in tileStates.indices) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight().weight(1f)
                    ) {

                        for(y in tileStates[x].indices) {
                            val tileStates = tileStates[x][y]
                            val revealedBorderWidth = tileLength / 32
                            val hiddenBorderWidth = tileLength / 8
                            Tile(
                                state = tileStates,
                                modifier = Modifier.fillMaxWidth().weight(1f),
                                revealedBorderWidth = revealedBorderWidth,
                                hiddenBorderWidth = hiddenBorderWidth,
                                textStyle = sizeAdjustedTextStyle
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Tile(
    state: TileState, modifier: Modifier = Modifier,
    revealedBorderWidth: Dp, hiddenBorderWidth: Dp,
    textStyle: TextStyle = TextStyle()
    ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(color = LocalMinesweeperBoardColorScheme.current.background)
            .run {
                if (state is TileState.Revealed) {
                    border(
                        width = revealedBorderWidth,
                        color = LocalMinesweeperBoardColorScheme.current.borderDark
                    )
                } else {
                    hiddenTile(
                        borderThickness = hiddenBorderWidth,
                        lightColor = LocalMinesweeperBoardColorScheme.current.borderLight,
                        darkColor = LocalMinesweeperBoardColorScheme.current.borderDark
                    )
                }
            }
    ) {
        when (state) {
            is TileState.Hidden -> {
                if (state.flagged) {
                    Icon(
                        imageVector = Icons.Filled.Flag,
                        tint = LocalMinesweeperBoardColorScheme.current.flag,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(fraction = .6f)
                    )
                }
            }

            TileState.Revealed.Mine -> {
                Icon(
                    painter = painterResource(Res.drawable.mine),
                    contentDescription = null,
                    tint = LocalMinesweeperBoardColorScheme.current.mine,
                    modifier = modifier.fillMaxSize(fraction = .6f)
                )
            }

            is TileState.Revealed.Number -> {
                state.value?.let { number ->
                    val color = when (number) {
                        1 -> LocalMinesweeperBoardColorScheme.current.one
                        2 -> LocalMinesweeperBoardColorScheme.current.two
                        3 -> LocalMinesweeperBoardColorScheme.current.three
                        4 -> LocalMinesweeperBoardColorScheme.current.four
                        5 -> LocalMinesweeperBoardColorScheme.current.five
                        6 -> LocalMinesweeperBoardColorScheme.current.six
                        7 -> LocalMinesweeperBoardColorScheme.current.seven
                        8 -> LocalMinesweeperBoardColorScheme.current.eight
                        else -> Color.Black
                    }
                    Text(
                        text = number.toString(),
                        textAlign = TextAlign.Center,
                        color = color,
                        textStyle = textStyle,
                        modifier = Modifier.fillMaxSize().wrapContentHeight(
                            align = Alignment.CenterVertically
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun Modifier.hiddenTile(
    borderThickness: Dp,
    lightColor: Color,
    darkColor: Color,
) = drawWithContent {
    drawContent()
    val thicknessPixel = borderThickness.toPx()
    val width = size.width
    val height = size.height

    drawPath(
        path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, height)
            lineTo(thicknessPixel, height - thicknessPixel)
            lineTo(thicknessPixel, thicknessPixel)
            lineTo(width - thicknessPixel, thicknessPixel)
            lineTo(width, 0f)
        },
        color = lightColor
    )
    drawPath(
        path = Path().apply {
            moveTo(0f, height)
            lineTo(thicknessPixel, height - thicknessPixel)
            lineTo(width - thicknessPixel, height - thicknessPixel)
            lineTo(width - thicknessPixel, thicknessPixel)
            lineTo(width, 0f)
            lineTo(width, height)
        },
        color = darkColor
    )
}

@Immutable
data class MinesweeperBoardColorScheme(
    val background: Color,
    val borderLight: Color,
    val borderDark: Color,
    val mine: Color,
    val flag: Color,
    val one: Color,
    val two: Color,
    val three: Color,
    val four: Color,
    val five: Color,
    val six: Color,
    val seven: Color,
    val eight: Color,
    val timerText: Color,
)

internal val LocalMinesweeperBoardColorScheme = compositionLocalOf {
    MinesweeperBoardColorScheme(
        background = Color(0xFFCBCBCB),
        borderLight = Color(0xFFFFFFFF),
        borderDark = Color(0xFF8F8F8F),
        mine = Color(0xFF000000),
        flag = Color(0xFFEB392A),
        one = Color(0xFF0000F5),
        two = Color(0xFF377E22),
        three = Color(0xFFFA3323),
        four = Color(0xFF00007B),
        five = Color(0xFF75140C),
        six = Color(0xFF377E7F),
        seven = Color(0xFF75147C),
        eight = Color(0xFF808080),
        timerText = Color(0xFFEA3324),
    )
}

private fun getAllPossilbeTileStates(): Set<TileState> {
    return setOf(
        TileState.Hidden(flagged = true),
        TileState.Hidden(flagged = false),
        TileState.Revealed.Mine,
        TileState.Revealed.Number(null),
        TileState.Revealed.Number(1),
        TileState.Revealed.Number(2),
        TileState.Revealed.Number(3),
        TileState.Revealed.Number(4),
        TileState.Revealed.Number(5),
        TileState.Revealed.Number(6),
        TileState.Revealed.Number(7),
        TileState.Revealed.Number(8),

        )
}