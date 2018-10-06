package xyz.mcnallydawes.pokedex.screens.main

sealed class MainPage
object SearchTrainersPage : MainPage()
object SearchPokemonPage : MainPage()

data class MainModel(val page: MainPage)
