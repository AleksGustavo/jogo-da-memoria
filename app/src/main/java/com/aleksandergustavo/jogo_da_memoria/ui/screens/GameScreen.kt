package com.aleksandergustavo.jogo_da_memoria.ui.screens

import com.aleksandergustavo.jogo_da_memoria.model.Card
import androidx.compose.foundation.lazy.grid.items
import androidx.lifecycle.viewmodel.compose.viewModel
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleksandergustavo.jogo_da_memoria.model.GameCategory
import com.aleksandergustavo.jogo_da_memoria.ui.components.GameHeader
import com.aleksandergustavo.jogo_da_memoria.ui.components.MemoryCard
import com.aleksandergustavo.jogo_da_memoria.ui.theme.MemoryGameTheme
import kotlinx.coroutines.delay
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.sp
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Brown
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Green
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Purple
import com.aleksandergustavo.jogo_da_memoria.ui.viewmodel.GameViewModel


/** Largura: altura das cartas (mesma proporção das artes, 2:3). */
private const val CardAspectRatio = 2f / 3f

@Composable
fun GameScreen(
    category: GameCategory,
    onBack: () -> Unit,
    onVictory: (Int, Int) -> Unit,
    viewModel: GameViewModel = viewModel() // 1. Injeta o ViewModel aqui
) {
    // 2. Observa os estados gerenciados pela sua regra de negócio
    val cards by viewModel.cards.collectAsState()
    val attempts by viewModel.attempts.collectAsState()
    val isGameOver by viewModel.isGameOver.collectAsState()
    val timeSeconds by viewModel.timeSeconds.collectAsState()

    // Formatação dos segundos
    val minutes = (timeSeconds / 60).toString().padStart(2, '0')
    val seconds = (timeSeconds % 60).toString().padStart(2, '0')
    val formattedTime = "$minutes:$seconds"
    val isPaused by viewModel.isPaused.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.restartGame(category)
    }

    // 3. Efeito enxuto: Apenas reage ao fim do jogo para trocar de tela
    LaunchedEffect(isGameOver) {
        if (isGameOver) {
            delay(450) // Mantém o pequeno delay original para o usuário ver o último par formado
            onVictory(attempts, timeSeconds)
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
            // 4. Conecta o Header aos dados e ações do ViewModel
            GameHeader(
                category = category,
                attempts = attempts,
                onBack = onBack,
                onRestart = { viewModel.restartGame(category) }
            )

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
                                    // 5. O clique na carta agora aciona o método do seu backend
                                    onClick = { viewModel.onCardClicked(card.id) },
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
                    Spacer(Modifier.height(12.dp))
                    Surface(shape = RoundedCornerShape(9.dp), color = Color(0xFFF5C27A)) {
                        Text(
                            text = "Tempo: $formattedTime",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF8A4A16),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))

            Button(
                onClick = { viewModel.toggleTimer() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Brown)
            ) {
                Icon(Icons.Filled.Pause, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    if(isPaused) "Retomar" else "Pausar", fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GamePreview() { MemoryGameTheme { GameScreen(GameCategory.ANIMALS, {},
    {} as (Int, Int) -> Unit) } }
