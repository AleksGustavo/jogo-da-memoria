package com.aleksandergustavo.jogo_da_memoria.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import com.aleksandergustavo.jogo_da_memoria.model.GameCategory
import com.aleksandergustavo.jogo_da_memoria.ui.screens.*
import android.media.MediaPlayer
import androidx.compose.ui.platform.LocalContext
import com.aleksandergustavo.jogo_da_memoria.R


private enum class Screen { SPLASH, HOME, CATEGORY, GAME, VICTORY }

@Composable
fun AppNavigation() {
    var screen by remember { mutableStateOf(Screen.SPLASH) };
    var category by remember { mutableStateOf(GameCategory.ANIMALS) };
    var attempts by remember { mutableIntStateOf(0) }
    var timeSeconds by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    var isMuted by remember { mutableStateOf(false) }

    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.happy_adventure).apply {
            isLooping = true
        }
    }

    LaunchedEffect(isMuted) {
        if (isMuted) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
    }

    DisposableEffect(Unit) {
        onDispose { mediaPlayer.release() }
    }

    BackHandler(enabled = screen != Screen.HOME && screen != Screen.SPLASH) {
        screen = when (screen) {
            Screen.CATEGORY -> Screen.HOME; Screen.GAME -> Screen.CATEGORY; Screen.VICTORY -> Screen.CATEGORY; else -> Screen.HOME
        }
    }
    when (screen) {
        Screen.SPLASH -> SplashScreen { screen = Screen.HOME }; Screen.HOME -> HomeScreen {
        screen = Screen.CATEGORY
    }; Screen.CATEGORY -> CategoryScreen(
        onSelect = {
            category = it
            screen = Screen.GAME
        },
        onSoundClick = {
            isMuted = !isMuted
        }

    ); Screen.GAME -> GameScreen(
        category = category,
        onBack = { screen = Screen.CATEGORY },
        onVictory = { att, time ->
            attempts = att
            timeSeconds = time
            screen = Screen.VICTORY
        }
    ); Screen.VICTORY -> VictoryScreen(
        attempts = attempts,
        timeSeconds = timeSeconds,
        { screen = Screen.GAME },
        { screen = Screen.CATEGORY },
        { screen = Screen.HOME })
    }
}
