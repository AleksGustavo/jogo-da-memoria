package com.aleksandergustavo.jogo_da_memoria.data

import com.aleksandergustavo.jogo_da_memoria.R
import com.aleksandergustavo.jogo_da_memoria.model.GameCategory
import com.aleksandergustavo.jogo_da_memoria.model.MemoryCardUi

object FakeGameData {
    private val data = mapOf(
        GameCategory.ANIMALS to listOf(
            "Golfinho" to R.drawable.dolphin,
            "Macaco" to R.drawable.monkey,
            "Tatu" to R.drawable.armadillo,
            "Papagaio" to R.drawable.parrot,
            "Pinguim" to R.drawable.penguin,
            "Leão" to R.drawable.lion,
            "Zebra" to R.drawable.zebra,
            "Elefante" to R.drawable.elephant
        ),
        GameCategory.FRUITS to listOf(
            "Maçã" to R.drawable.apple,
            "Mamão" to R.drawable.papaya,
            "Limão" to R.drawable.lime,
            "Banana" to R.drawable.banana,
            "Cereja" to R.drawable.cherry,
            "Abacate" to R.drawable.avocado,
            "Uva" to R.drawable.grape,
            "Kiwi" to R.drawable.kiwi
        ),
        GameCategory.OBJECTS to listOf(
            "Televisão" to R.drawable.television,
            "Telescópio" to R.drawable.telescope,
            "Bicicleta" to R.drawable.bicycle,
            "Mochila" to R.drawable.backpack,
            "Luminária" to R.drawable.lamp,
            "Celular" to R.drawable.phone,
            "Câmera" to R.drawable.camera,
            "Máquina de Lavar" to R.drawable.washing_machine
        )
    )

    /** Primeira carta de cada categoria — usada como miniatura de destaque no menu. */
    fun thumbnail(category: GameCategory): Int = data.getValue(category).first().second

    // TODO: substituir pelos dados fornecidos pela camada de negócio/backend.
    fun cards(category: GameCategory): List<MemoryCardUi> =
        data.getValue(category).flatMapIndexed { pair, (name, imageRes) ->
            listOf(
                MemoryCardUi(pair * 2, pair, name, imageRes),
                MemoryCardUi(pair * 2 + 1, pair, name, imageRes)
            )
        }.shuffled()
}
