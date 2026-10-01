package com.aleksandergustavo.jogo_da_memoria.ui.viewmodel
import MemoryGame
import com.aleksandergustavo.jogo_da_memoria.model.Card
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleksandergustavo.jogo_da_memoria.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {
    private val imageList = listOf(
        R.drawable.acerola,
        R.drawable.apple,
        R.drawable.armadillo,
        R.drawable.avocado,
        R.drawable.backpack,
        R.drawable.banana,
        R.drawable.bicycle,
        R.drawable.camera,
        R.drawable.cherry,
        R.drawable.clock,
        R.drawable.compass,
        R.drawable.dolphin,
        R.drawable.elephant,
        R.drawable.flamingo,
        R.drawable.grape,
        R.drawable.kiwi,
        R.drawable.lamp,
        R.drawable.lighthouse,
        R.drawable.lime,
        R.drawable.lion,
        R.drawable.monkey,
        R.drawable.orange,
        R.drawable.papaya,
        R.drawable.parrot,
        R.drawable.peach,
        R.drawable.penguin,
        R.drawable.phone,
        R.drawable.pineapple,
        R.drawable.pomegranate,
        R.drawable.safe,
        R.drawable.ship,
        R.drawable.sloth,
        R.drawable.spyglass,
        R.drawable.strawberry,
        R.drawable.telescope,
        R.drawable.television,
        R.drawable.toucan,
        R.drawable.washing_machine,
        R.drawable.zebra
        )

    // instanciando a classe
    private var game = MemoryGame(imageList)

    // estados que o Compose vai observar
    private val _cards = MutableStateFlow(game.cards)
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()

    private val _attempts = MutableStateFlow(game.attempts)
    val attempts: StateFlow<Int> = _attempts.asStateFlow()

    private val _isGameOver = MutableStateFlow(game.isGameOver)
    val isGameOver: StateFlow<Boolean> = _isGameOver.asStateFlow()

    fun onCardClicked(index: Int) {
        if (game.isTouchBlocked) return

        game.chooseCard(index)

        // atualiza a tela imediatamente para mostrar a carta virada
        updateUiState()

        // se o jogo bloqueou os toques, significa que duas cartas estão viradas e ele está comparando.
        if(game.isTouchBlocked) {
            viewModelScope.launch {
                delay(1000) // delay de 1 segundo para fazer a comparação e o jogador conseguir ver a carta que está sendo virada

                // metodo utilizado para desvirar a carta caso o jogador tenha errado o par
                game.resolveTurn()

                // atualizando a tela
                updateUiState()
            }
        }
    }

    fun restartGame() {
        game = MemoryGame(imageList)
        updateUiState()
    }

    private fun updateUiState() {
        _cards.value = game.cards.toList()
        _attempts.value = game.attempts
        _isGameOver.value = game.isGameOver
    }
}