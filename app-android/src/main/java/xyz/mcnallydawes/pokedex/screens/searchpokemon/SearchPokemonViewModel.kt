package xyz.mcnallydawes.pokedex.screens.searchpokemon

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.launch
import xyz.mcnallydawes.pokedex.Failure
import xyz.mcnallydawes.pokedex.Success
import xyz.mcnallydawes.pokedex.domain.boundary.ListPokemonInteractor
import xyz.mcnallydawes.pokedex.domain.boundary.SearchPokemonInteractor
import xyz.mcnallydawes.pokedex.domain.request.ListPokemonRequest
import xyz.mcnallydawes.pokedex.domain.request.SearchPokemonRequest
import kotlin.coroutines.experimental.CoroutineContext

class SearchPokemonViewModel(
        private val searchPokemonModel: SearchPokemonModel,
        private val listPokemon: ListPokemonInteractor,
        private val searchPokemon: SearchPokemonInteractor,
        private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _model: MutableLiveData<SearchPokemonModel> = MutableLiveData()
    val model: LiveData<SearchPokemonModel> = _model

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error

    fun load() {
        launch(coroutineContext) {
            val response = listPokemon.execute(ListPokemonRequest())
            when (response) {
                is Success -> {
                    searchPokemonModel.pokemon = response.value.pokemon.toMutableList()
                    _model.postValue(searchPokemonModel)
                }
                is Failure -> _error.postValue(response.throwable)
            }
        }
    }

    fun search(query: String) {
        launch(coroutineContext) {
            val response = searchPokemon.execute(SearchPokemonRequest(query))
            when (response) {
                is Success -> {
                    val pokemon = response.value.pokemon
                    if (searchPokemonModel.lastQuery == query) {
                        searchPokemonModel.pokemon.addAll(pokemon)
                    } else {
                        searchPokemonModel.pokemon = pokemon.toMutableList()
                    }
                    _model.postValue(searchPokemonModel)
                }
                is Failure -> _error.postValue(response.throwable)
            }
        }
    }

}