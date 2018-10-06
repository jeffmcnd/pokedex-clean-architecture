package xyz.mcnallydawes.pokedex.domain.entity

sealed class Type(val id: String)
object Grass : Type("1")
object Fire : Type("2")
object Water : Type("3")
object Bug: Type("4")
object Normal: Type("5")
object Flying: Type("6")
object Poison: Type("7")
