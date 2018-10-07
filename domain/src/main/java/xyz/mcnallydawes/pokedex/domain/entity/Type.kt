package xyz.mcnallydawes.pokedex.domain.entity

sealed class Type(val id: String, val name: String)
object Grass : Type("1", "Grass")
object Fire : Type("2", "Fire")
object Water : Type("3", "Water")
object Bug: Type("4", "Bug")
object Normal: Type("5", "Normal")
object Flying: Type("6", "Flying")
object Poison: Type("7", "Poison")
