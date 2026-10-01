data class Card (
    val id: Int, // Identificador único para cada carta
    val imageResId: Int, // ID para cada imagem
    var isFlipped: Boolean = false, // Se a carta está virada
    var isMatched: Boolean = false, // Se o par já foi encontrado
)