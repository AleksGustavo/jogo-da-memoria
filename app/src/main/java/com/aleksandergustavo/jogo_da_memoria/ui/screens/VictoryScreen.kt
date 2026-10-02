package com.aleksandergustavo.jogo_da_memoria.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleksandergustavo.jogo_da_memoria.ui.theme.MemoryGameTheme
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Yellow
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.filled.Star

@Composable
fun VictoryScreen(
    attempts: Int,
    timeSeconds: Int,
    onReplay: () -> Unit,
    onCategories: () -> Unit,
    onHome: () -> Unit
) {
    var start by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (start) 1f else .55f, spring(), label = "victory")
    LaunchedEffect(Unit) { start = true }
    val minutes = (timeSeconds / 60).toString().padStart(2, '0')
    val seconds = (timeSeconds % 60).toString().padStart(2, '0')

    // Ganho de estrelas ao finalizar a partida
    val earnedStars = when {
        attempts <= 15 -> 3
        attempts <= 20 -> 2
        else -> 1
    }


    Column(
        Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFFFBDF), Color(0xFFF3EDFF))))
            .padding(26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .size(96.dp)
                .scale(scale)
                .background(Yellow, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.EmojiEvents,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(52.dp)
            )
        }
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            for (i in 1..3) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = if(i <= earnedStars) Color(0xFFFFC107) else Color(0xFF0E0E0E),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(horizontal = 4.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        Text(
            "Parabéns!",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(Modifier.height(6.dp))
        Text(
            "Você encontrou todos os pares!",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(18.dp))
        Text(
            "Tentativas: $attempts",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(8.dp))
        Text(text = "Tempo total: $minutes:$seconds",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFFB8702F),
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(30.dp))
        Button(
            onClick = onReplay,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp)
        ) { Text("JOGAR NOVAMENTE") }
        Spacer(Modifier.height(10.dp))
        OutlinedButton(
            onClick = onCategories,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(18.dp)
        ) { Text("ESCOLHER OUTRA CATEGORIA") }
        TextButton(onClick = onHome) { Text("VOLTAR AO INÍCIO") }
    }
}

@Preview(showBackground = true)
@Composable
private fun VictoryPreview() {
    MemoryGameTheme { VictoryScreen(12, 85, {}, {}, onHome = {}) }
}
