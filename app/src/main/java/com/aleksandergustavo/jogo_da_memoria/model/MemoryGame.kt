import com.aleksandergustavo.jogo_da_memoria.model.Card

class MemoryGame(private val images: List<Int>) {
    var cards: List<Card> = emptyList()
        private set

    // variaveis de controle
    private var indexOfSingleSelectedCard: Int? = null
    var isTouchBlocked: Boolean = false
        private set
    var isGameOver: Boolean = false
        private set
    var attempts: Int = 0
        private set

    init {
        setupGame()
    }

    // Regras

    // 1º Tarefa: Embaralhar pares
    private fun setupGame() {
        // Pegando a lista de imagens, duplicando para formar os pares e embaralhando
        val pairs = (images + images).shuffled()
        // Transformando a lista de imagens em objetos do tipo Card
        cards = pairs.mapIndexed { index, imageId -> Card(id = index, imageResId = imageId) }
    }

    // 2º Tarefa: Virar carta
    fun chooseCard(index: Int) {
        val clickedCard = cards[index]
        if(isTouchBlocked || clickedCard.isFlipped || clickedCard.isMatched) {
            return
        }

        // 3º e 4º Tarefa: Lógica de comparação e bloqueio
        if (indexOfSingleSelectedCard == null) {
            // Cenário A: Primeira carta do par sendo virada
            clickedCard.isFlipped = true
            indexOfSingleSelectedCard = index
        } else {
            // Cenário B: Segunda carta do par sendo virada
            clickedCard.isFlipped = true
            attempts++
            isTouchBlocked = true // bloquenado toques enquanto compara as cartas

            checkForMatch(indexOfSingleSelectedCard!!, index)
            indexOfSingleSelectedCard = null
        }
    }

    private fun checkForMatch(index1: Int, index2: Int) {
        val card1 = cards[index1]
        val card2 = cards[index2]

        if(card1.imageResId == card2.imageResId) {
            // acertou o par
            card1.isMatched = true
            card2.isMatched = true
            isTouchBlocked = false // libera o toque na tela imediatamente

            // 5º Tarefa: Checar o fim da partida
            checkGameOver()
        }
    }

    fun resolveTurn() {
        cards.forEach { card ->
            if (!card.isMatched) {
                card.isFlipped = false
            }
        }

        isTouchBlocked = false
    }

    // 5º Tarefa: Checar o fim da partida
    private fun checkGameOver() {
        // se todas as cartas da lista estiverem com isMatched = true, acabou o jogo
        isGameOver = cards.all { it.isMatched }
    }
}


