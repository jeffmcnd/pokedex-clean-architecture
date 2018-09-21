package xyz.mcnallydawes.pokedex

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import xyz.mcnallydawes.pokedex.infraroom.source.RoomPokemonSource
import xyz.mcnallydawes.pokedex.screens.pokemondetails.PokemonDetailsViewModel
import xyz.mcnallydawes.pokedex.screens.pokemondetails.PokemonModel
import xyz.mcnallydawes.pokedex.screens.searchpokemon.SearchPokemonModel
import xyz.mcnallydawes.pokedex.screens.searchpokemon.SearchPokemonViewModel


val interactorModule = module {

    factory { GetPokemon(get<RoomPokemonSource>()) }

    factory { SearchPokemon(get<RoomPokemonSource>()) }

    factory { ListPokemon(get<RoomPokemonSource>()) }

}

val viewModelModule = module {

    viewModel {
        val model = SearchPokemonModel("", mutableListOf())
        SearchPokemonViewModel(
                model,
                get<ListPokemon>(),
                get<SearchPokemon>(),
                kotlinx.coroutines.experimental.IO
        )
    }

    viewModel {
        val model = PokemonModel()
        PokemonDetailsViewModel(
                model,
                kotlinx.coroutines.experimental.IO,
                get<GetPokemon>()
        )
    }

}
