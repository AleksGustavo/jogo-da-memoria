package com.aleksandergustavo.jogo_da_memoria.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Card // O componente visual do Compose
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleksandergustavo.jogo_da_memoria.R
import com.aleksandergustavo.jogo_da_memoria.ui.theme.Purple
import com.aleksandergustavo.jogo_da_memoria.model.Card as ModelCard

private const val CardAspectRatio = 2f / 3f

@Composable
fun MemoryCard(
    card: ModelCard, // Passa a usar o apelido aqui
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    accentColor: Color = Purple
) {
    val visible = card.isFlipped || card.isMatched
    val rotation by animateFloatAsState(
        targetValue = if (visible) 180f else 0f,
        label = "memoryCardFlip"
    )

    Card(
        modifier = modifier
            .aspectRatio(CardAspectRatio)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            }
            .clickable(enabled = !visible, onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                card.isMatched -> Color(0xFFEAFBEF)
                visible -> Color.White
                else -> Color.Transparent
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (visible) 3.dp else 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { rotationY = if (visible) 180f else 0f },
            contentAlignment = Alignment.Center
        ) {
            if (visible) {
                Image(
                    painter = painterResource(card.imageResId),
                    contentDescription = null, // Removido o card.label que causava o erro
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Fit
                )
                if (card.isMatched) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Par encontrado",
                        tint = Color(0xFF2E9E4D),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp)
                            .background(Color.White, CircleShape)
                            .padding(2.dp)
                            .size(20.dp)
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(accentColor, lerp(accentColor, Color.Black, 0.25f))
                            )
                        )
                        .border(2.dp, Color.White.copy(alpha = 0.35f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .background(Color.White.copy(alpha = 0.22f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.QuestionMark,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCardBack() {
    MemoryCard(
        // Parâmetros limpos, usando apenas os existentes na nossa data class
        card = ModelCard(id = 1, imageResId = R.drawable.monkey),
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCardFront() {
    MemoryCard(
        // Parâmetros limpos, usando apenas os existentes na nossa data class
        card = ModelCard(
            id = 1,
            imageResId = R.drawable.monkey,
            isFlipped = true
        ),
        onClick = {}
    )
}