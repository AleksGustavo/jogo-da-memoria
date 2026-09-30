package com.aleksandergustavo.jogo_da_memoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aleksandergustavo.jogo_da_memoria.navigation.AppNavigation
import com.aleksandergustavo.jogo_da_memoria.ui.theme.MemoryGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryGameTheme {
                AppNavigation()
            }
        }
    }
}
