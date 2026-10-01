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

    // Embaralhar pares
    private fun setupGame() {
        // Pegando a lista de imagens, duplicando para formar os pares e embaralhando
        val pairs = (images + images).shuffled()
        // Transformando a lista de imagens em objetos do tipo Card
        cards = pairs.mapIndexed { index, imageId -> Card(id = index, imageResId = imageId) }
    }
}

