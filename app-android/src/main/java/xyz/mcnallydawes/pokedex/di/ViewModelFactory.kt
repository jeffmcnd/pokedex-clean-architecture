package xyz.mcnallydawes.pokedex.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import xyz.mcnallydawes.pokedex.boundary.ListPokemonInteractor
import xyz.mcnallydawes.pokedex.boundary.SearchPokemonInteractor
import xyz.mcnallydawes.pokedex.searchpokemon.SearchPokemonModel
import xyz.mcnallydawes.pokedex.searchpokemon.SearchPokemonViewModel
import kotlin.coroutines.experimental.CoroutineContext

class ViewModelFactory(
        private val listPokemon: ListPokemonInteractor,
        private val searchPokemon: SearchPokemonInteractor
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
                else -> super.create(modelClass)
            }

}
