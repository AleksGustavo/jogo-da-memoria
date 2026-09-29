package com.aleksandergustavo.jogo_da_memoria.navigation
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import com.aleksandergustavo.jogo_da_memoria.model.GameCategory
import com.aleksandergustavo.jogo_da_memoria.ui.screens.*
private enum class Screen{SPLASH,HOME,CATEGORY,GAME,VICTORY}
@Composable fun AppNavigation(){var screen by remember{mutableStateOf(Screen.SPLASH)};var category by remember{mutableStateOf(GameCategory.ANIMALS)};var attempts by remember{mutableIntStateOf(0)}
 BackHandler(enabled=screen!=Screen.HOME&&screen!=Screen.SPLASH){screen=when(screen){Screen.CATEGORY->Screen.HOME;Screen.GAME->Screen.CATEGORY;Screen.VICTORY->Screen.CATEGORY;else->Screen.HOME}}
 when(screen){Screen.SPLASH->SplashScreen{screen=Screen.HOME};Screen.HOME->HomeScreen{screen=Screen.CATEGORY};Screen.CATEGORY->CategoryScreen{category=it;screen=Screen.GAME};Screen.GAME->GameScreen(category,{screen=Screen.CATEGORY}){attempts=it;screen=Screen.VICTORY};Screen.VICTORY->VictoryScreen(attempts,{screen=Screen.GAME},{screen=Screen.CATEGORY},{screen=Screen.HOME})}}
