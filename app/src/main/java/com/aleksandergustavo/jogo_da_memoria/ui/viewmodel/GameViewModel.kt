package com.aleksandergustavo.jogo_da_memoria.ui.viewmodel
import com.aleksandergustavo.jogo_da_memoria.model.GameCategory
import MemoryGame
import com.aleksandergustavo.jogo_da_memoria.model.Card
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aleksandergustavo.jogo_da_memoria.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {

    // instanciando a classe
    private var game = MemoryGame(emptyList())

    // estados que o Compose vai observar
    private val _cards = MutableStateFlow(game.cards)
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()

    private val _attempts = MutableStateFlow(game.attempts)
    val attempts: StateFlow<Int> = _attempts.asStateFlow()

    private val _isGameOver = MutableStateFlow(game.isGameOver)
    val isGameOver: StateFlow<Boolean> = _isGameOver.asStateFlow()

    // Cronômetro
    private val _timeSeconds = MutableStateFlow(0)
    val timeSeconds: StateFlow<Int> = _timeSeconds.asStateFlow()

    private var timerJob: Job? = null

    // Inicia o cronômetro
    private fun startTime() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _timeSeconds.value += 1
            }
        }
    }

    // Parar o cronômetro
    private fun stopTimer() {
        timerJob?.cancel()
    }

    private fun getImagesByCategory(category: GameCategory): List<Int> {
        return when (category) {
            GameCategory.FRUITS -> listOf(
                R.drawable.apple,
                R.drawable.papaya,
                R.drawable.avocado,
                R.drawable.banana,
                R.drawable.cherry,
                R.drawable.grape,
                R.drawable.kiwi,
                R.drawable.lime
            )
            GameCategory.ANIMALS -> listOf(
                R.drawable.armadillo,
                R.drawable.zebra,
                R.drawable.parrot,
                R.drawable.dolphin,
                R.drawable.elephant,
                R.drawable.monkey,
                R.drawable.penguin,
                R.drawable.lion
            )
            GameCategory.OBJECTS -> listOf(
                R.drawable.washing_machine,
                R.drawable.telescope,
                R.drawable.bicycle,
                R.drawable.backpack,
                R.drawable.camera,
                R.drawable.lamp,
                R.drawable.phone,
                R.drawable.television
            )
        }
    }

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

    fun restartGame(category: GameCategory) {
        val selectedImages = getImagesByCategory(category)
        game = MemoryGame(selectedImages)
        _timeSeconds.value = 0
        startTime()
        updateUiState()
    }

    private fun updateUiState() {
        // .map para criar cópias exatas forçando o Compose redesenhar as cartas
        _cards.value = game.cards.map { it.copy() }
        _attempts.value = game.attempts
        _isGameOver.value = game.isGameOver

        if(game.isGameOver) {
            stopTimer()
        }
    }
}