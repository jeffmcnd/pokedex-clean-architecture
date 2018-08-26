package xyz.mcnallydawes.domain.entity

data class Pokemon(
        val id: String,
        val name: String,
        val types: Set<Type>
)