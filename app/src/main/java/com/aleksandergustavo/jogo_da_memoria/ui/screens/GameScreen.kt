package com.aleksandergustavo.jogo_da_memoria.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.aleksandergustavo.jogo_da_memoria.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleksandergustavo.jogo_da_memoria.data.FakeGameData
import com.aleksandergustavo.jogo_da_memoria.model.GameCategory
import com.aleksandergustavo.jogo_da_memoria.ui.components.GameHeader
import com.aleksandergustavo.jogo_da_memoria.ui.components.MemoryCard
import com.aleksandergustavo.jogo_da_memoria.ui.theme.MemoryGameTheme
import kotlinx.coroutines.delay

/** Largura : altura das cartas (mesma proporção das artes, 2:3). */
private const val CardAspectRatio = 2f / 3f

@Composable
fun GameScreen(category: GameCategory, onBack: () -> Unit, onVictory: (Int) -> Unit) {
    var cards by remember(category) { mutableStateOf(FakeGameData.cards(category)) }
    var attempts by remember(category) { mutableIntStateOf(0) }
    var locked by remember(category) { mutableStateOf(false) }
    var victorySent by remember(category) { mutableStateOf(false) }

    fun restart() {
        cards = FakeGameData.cards(category)
        attempts = 0
        locked = false
        victorySent = false
    }

    fun choose(id: Int) {
        if (locked) return
        val selected = cards.firstOrNull { it.id == id } ?: return
        if (selected.isFlipped || selected.isMatched) return
        cards = cards.map { if (it.id == id) it.copy(isFlipped = true) else it }
        if (cards.count { it.isFlipped && !it.isMatched } == 2) {
            attempts++
            locked = true
        }
    }

    LaunchedEffect(cards, locked) {
        if (locked) {
            val open = cards.filter { it.isFlipped && !it.isMatched }
            if (open.size == 2) {
                delay(650)
                cards = if (open[0].pairId == open[1].pairId) {
                    cards.map { if (it.pairId == open[0].pairId) it.copy(isMatched = true) else it }
                } else {
                    cards.map { if (it.id == open[0].id || it.id == open[1].id) it.copy(isFlipped = false) else it }
                }
                locked = false
            }
        }
        if (!victorySent && cards.isNotEmpty() && cards.all { it.isMatched }) {
            victorySent = true
            delay(450)
            onVictory(attempts)
        }
    }

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bg_game),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            GameHeader(category, attempts, onBack, ::restart)
            Spacer(Modifier.height(14.dp))
            Surface(
                shape = RoundedCornerShape(26.dp),
                color = Color(0xFFFBF3E3),
                border = BorderStroke(4.dp, Color(0xFFB8702F)),
                shadowElevation = 6.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    BoxWithConstraints(Modifier.fillMaxWidth()) {
                        val gap = 8.dp
                        val boardWidth = maxWidth
                        val cellWidth = (boardWidth - gap * 3) / 4
                        val cellHeight = cellWidth / CardAspectRatio
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(cellHeight * 4 + gap * 3),
                            horizontalArrangement = Arrangement.spacedBy(gap),
                            verticalArrangement = Arrangement.spacedBy(gap),
                            userScrollEnabled = false
                        ) {
                            items(cards, key = { it.id }) { card ->
                                MemoryCard(
                                    card = card,
                                    onClick = { choose(card.id) },
                                    modifier = Modifier.fillMaxWidth(),
                                    accentColor = category.color
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Surface(shape = RoundedCornerShape(10.dp), color = Color(0xFFF5C27A)) {
                        Text(
                            "Encontre os 8 pares para vencer!",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8A4A16),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GamePreview() { MemoryGameTheme { GameScreen(GameCategory.ANIMALS, {}, {}) } }
