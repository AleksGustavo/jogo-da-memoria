package com.aleksandergustavo.jogo_da_memoria.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aleksandergustavo.jogo_da_memoria.data.FakeGameData
import com.aleksandergustavo.jogo_da_memoria.model.GameCategory

private val Wood = Color(0xFFA9683A)
private val Sun = Color(0xFFFFC928)

@Composable
private fun RoundYellowButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Surface(shape = CircleShape, color = Sun, shadowElevation = 4.dp) {
        IconButton(onClick = onClick, modifier = Modifier.size(48.dp)) { content() }
    }
}

@Composable
fun GameHeader(category: GameCategory, attempts: Int, onBack: () -> Unit, onRestart: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RoundYellowButton(onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = Color.White) }
        Surface(shape = RoundedCornerShape(20.dp), color = Wood, shadowElevation = 6.dp) {
            Column(
                Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(FakeGameData.thumbnail(category)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Text(
                        category.title,
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Text("Tentativas: $attempts", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
        RoundYellowButton(onRestart) { Icon(Icons.Default.Refresh, "Reiniciar", tint = Color.White) }
    }
}
