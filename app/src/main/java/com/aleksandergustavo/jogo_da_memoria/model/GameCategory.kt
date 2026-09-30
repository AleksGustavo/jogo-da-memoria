package com.aleksandergustavo.jogo_da_memoria.model

import androidx.compose.ui.graphics.Color
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Blue
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Green
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Orange

enum class GameCategory(val title: String, val subtitle: String, val color: Color, val emoji: String) {
    FRUITS("Frutas", "Sabores e cores da natureza", Green, "\uD83C\uDF4E"),
    ANIMALS("Animais", "Bichos de todo o mundo", Blue, "\uD83D\uDC12"),
    OBJECTS("Objetos", "Coisas do dia a dia", Orange, "\uD83C\uDFC0")
}
