package com.aleksandergustavo.jogo_da_memoria.model

import androidx.annotation.DrawableRes

data class MemoryCardUi(
    val id: Int,
    val pairId: Int,
    val label: String,
    @DrawableRes val imageRes: Int,
    val isFlipped: Boolean = false,
    val isMatched: Boolean = false
)
