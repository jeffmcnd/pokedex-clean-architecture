package xyz.mcnallydawes.pokedex.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import xyz.mcnallydawes.pokedex.boundary.GetPokemonInteractor
import xyz.mcnallydawes.pokedex.boundary.ListPokemonInteractor
import xyz.mcnallydawes.pokedex.boundary.SearchPokemonInteractor
import xyz.mcnallydawes.pokedex.screens.pokemondetails.PokemonDetailsViewModel
import xyz.mcnallydawes.pokedex.screens.pokemondetails.PokemonModel
import xyz.mcnallydawes.pokedex.screens.searchpokemon.SearchPokemonModel
import xyz.mcnallydawes.pokedex.screens.searchpokemon.SearchPokemonViewModel
import kotlin.coroutines.experimental.CoroutineContext

class ViewModelFactory(
        private val listPokemon: ListPokemonInteractor,
        private val searchPokemon: SearchPokemonInteractor,
        private val getPokemon: GetPokemonInteractor
) : ViewModelProvider.NewInstanceFactory() {

    private val defaultCoroutineContext: CoroutineContext = kotlinx.coroutines.experimental.IO

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            when {
                modelClass.isAssignableFrom(SearchPokemonViewModel::class.java) ->
                    SearchPokemonViewModel(
                            SearchPokemonModel("", mutableListOf()),
                            listPokemon,
                            searchPokemon,
                            defaultCoroutineContext
                    ) as T
                modelClass.isAssignableFrom(PokemonDetailsViewModel::class.java) ->
                    PokemonDetailsViewModel(
                            PokemonModel(),
                            defaultCoroutineContext,
                            getPokemon
                    ) as T
                else -> super.create(modelClass)
            }

}
