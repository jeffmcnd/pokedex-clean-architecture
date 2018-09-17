package xyz.mcnallydawes.pokedex.screens.pokemondetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.launch
import xyz.mcnallydawes.pokedex.Failure
import xyz.mcnallydawes.pokedex.Success
import xyz.mcnallydawes.pokedex.boundary.GetPokemonInteractor
import xyz.mcnallydawes.pokedex.request.GetPokemonRequest
import kotlin.coroutines.experimental.CoroutineContext

class PokemonDetailsViewModel(
        private val pokemonModel: PokemonModel,
        private val coroutineContext: CoroutineContext,
        private val getPokemon: GetPokemonInteractor
) : ViewModel() {

    private val _model: MutableLiveData<PokemonModel> = MutableLiveData()
    val model: LiveData<PokemonModel> = _model

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error

    fun load(id: String) {
        launch(coroutineContext) {
            _model.postValue(pokemonModel.copy(isLoading = true))
            val response = getPokemon.execute(GetPokemonRequest(id))
            when (response) {
                is Success -> {
                    val pokemon = response.value.pokemon
                    _model.postValue(pokemonModel.copy(pokemon = pokemon))
                }
                is Failure -> _error.postValue(response.throwable)
            }
            _model.postValue(pokemonModel.copy(isLoading = false))
        }
    }

}