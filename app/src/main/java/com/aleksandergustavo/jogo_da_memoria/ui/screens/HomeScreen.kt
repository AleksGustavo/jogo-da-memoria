package com.aleksandergustavo.jogo_da_memoria.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Blue
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Green
import com.aleksandergustavo.jogo_da_memoria.ui.theme.MemoryGameTheme
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Orange
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Purple

@Composable
fun HomeScreen(onPlay: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFF4F0FF), Color(0xFFEAF8FF))))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(104.dp)
                    .background(
                        Brush.linearGradient(listOf(Purple, Blue)),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Psychology,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(56.dp)
                )
            }
            Spacer(Modifier.height(24.dp))
            Text("Jogo da Memória", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(10.dp))
            Text(
                "Teste sua memória e encontre todos os pares!",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF625D70),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(28.dp))
            Surface(shape = RoundedCornerShape(22.dp), color = Color.White, shadowElevation = 6.dp) {
                Row(
                    Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    HomeStat(Icons.Filled.Extension, "3", "categorias", Blue)
                    HomeStat(Icons.Filled.Psychology, "24", "cartas", Purple)
                    HomeStat(Icons.Filled.Timer, "8", "pares cada", Green)
                }
            }
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = onPlay,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Purple)
            ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("JOGAR", fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
            }
            Spacer(Modifier.height(12.dp))
            Text("3 categorias • 8 pares • muita diversão", color = Orange, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun HomeStat(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(22.dp))
        Spacer(Modifier.height(2.dp))
        Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        Text(label, fontSize = 11.sp, color = Color(0xFF8A8496))
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() { MemoryGameTheme { HomeScreen {} } }
