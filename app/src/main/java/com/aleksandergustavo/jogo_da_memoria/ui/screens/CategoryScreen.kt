package com.aleksandergustavo.jogo_da_memoria.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aleksandergustavo.jogo_da_memoria.R
import com.aleksandergustavo.jogo_da_memoria.model.GameCategory
import com.aleksandergustavo.jogo_da_memoria.ui.theme.MemoryGameTheme

// Tamanho original da arte do menu (bg_menu.jpg)
private const val ImgW = 873f
private const val ImgH = 1600f

/** Área clicável em pixels da imagem original: x1, y1, x2, y2. */
private data class Hotspot(val x1: Float, val y1: Float, val x2: Float, val y2: Float)

private val FruitsArea = Hotspot(172f, 805f, 700f, 947f)
private val AnimalsArea = Hotspot(172f, 973f, 700f, 1115f)
private val ObjectsArea = Hotspot(172f, 1142f, 700f, 1285f)
private val StarArea = Hotspot(530f, 20f, 700f, 170f)
private val SoundArea = Hotspot(700f, 20f, 860f, 170f)

@Composable
fun CategoryScreen(
    onSelect: (GameCategory) -> Unit,
    onStarClick: () -> Unit = {},
    onSoundClick: () -> Unit = {}
) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val density = LocalDensity.current
        val wPx = with(density) { maxWidth.toPx() }
        val hPx = with(density) { maxHeight.toPx() }
        val scaleX = wPx / ImgW
        val scaleY = hPx / ImgH

        fun pxToDp(px: Float): Dp = (px / density.density).dp

        Image(
            painter = painterResource(R.drawable.bg_menu),
            contentDescription = "Memory Game - escolha o tema",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        @Composable
        fun Hit(area: Hotspot, category: GameCategory) {
            Box(
                Modifier
                    .offset(x = pxToDp(area.x1 * scaleX), y = pxToDp(area.y1 * scaleY))
                    .width(pxToDp((area.x2 - area.x1) * scaleX))
                    .height(pxToDp((area.y2 - area.y1) * scaleY))
                    .clickable { onSelect(category) }
            )
        }

        @Composable
        fun HitAction(area: Hotspot, onClick: () -> Unit) {
            Box(
                Modifier
                    .offset(x = pxToDp(area.x1 * scaleX), y = pxToDp(area.y1 * scaleY))
                    .width(pxToDp((area.x2 - area.x1) * scaleX))
                    .height(pxToDp((area.y2 - area.y1) * scaleY))
                    .clickable { onClick() }
            )
        }

        Hit(FruitsArea, GameCategory.FRUITS)
        Hit(AnimalsArea, GameCategory.ANIMALS)
        Hit(ObjectsArea, GameCategory.OBJECTS)
        HitAction(StarArea, onStarClick)
        HitAction(SoundArea, onSoundClick)
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryPreview() {
    MemoryGameTheme { CategoryScreen(onSelect = {})}
}
