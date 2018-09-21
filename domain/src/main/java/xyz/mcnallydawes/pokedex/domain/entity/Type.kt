package xyz.mcnallydawes.pokedex.domain.entity

sealed class Type(val id: String)
object Grass : Type("1")
object Fire : Type("2")
object Water : Type("3")
