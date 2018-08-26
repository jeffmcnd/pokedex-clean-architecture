package xyz.mcnallydawes.domain.entity

import java.util.*

data class Trainer(
        val id: String? = null,
        val name: String,
        val starters: MutableList<Pokemon> = ArrayList()
)