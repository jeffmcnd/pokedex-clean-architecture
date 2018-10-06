package xyz.mcnallydawes.pokedex

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import xyz.mcnallydawes.pokedex.infrainmemory.source.HashMapPokemonSource
import xyz.mcnallydawes.pokedex.infrainmemory.source.HashMapTrainerSource
import xyz.mcnallydawes.pokedex.screens.main.MainModel
import xyz.mcnallydawes.pokedex.screens.main.MainViewModel
import xyz.mcnallydawes.pokedex.screens.main.SearchTrainersPage
import xyz.mcnallydawes.pokedex.screens.pokemondetails.PokemonDetailsViewModel
import xyz.mcnallydawes.pokedex.screens.pokemondetails.PokemonModel
import xyz.mcnallydawes.pokedex.screens.searchpokemon.SearchPokemonModel
import xyz.mcnallydawes.pokedex.screens.searchpokemon.SearchPokemonViewModel
import xyz.mcnallydawes.pokedex.screens.searchtrainers.SearchTrainersModel
import xyz.mcnallydawes.pokedex.screens.searchtrainers.SearchTrainersViewModel


val interactorModule = module {

    factory { AddPokemonToTrainer(get<HashMapTrainerSource>(), get<HashMapPokemonSource>()) }

    factory { CreateTrainer(get<HashMapTrainerSource>()) }

    factory { DeletePokemonFromTrainer(get<HashMapTrainerSource>()) }

    factory { GetPokemon(get<HashMapPokemonSource>()) }

    factory { GetTrainer(get<HashMapTrainerSource>()) }

    factory { ListPokemon(get<HashMapPokemonSource>()) }

    factory { ListTrainers(get<HashMapTrainerSource>()) }

    factory { SearchPokemon(get<HashMapPokemonSource>()) }

    factory { SearchTrainers(get<HashMapTrainerSource>()) }

}

val viewModelModule = module {

    viewModel {
        val model = MainModel(SearchTrainersPage)
        MainViewModel(model)
    }

    viewModel {
        SearchTrainersViewModel(
                SearchTrainersModel("", mutableListOf()),
                get<ListTrainers>(),
                get<SearchTrainers>(),
                kotlinx.coroutines.experimental.IO
        )
    }

    viewModel {
        SearchPokemonViewModel(
                SearchPokemonModel("", mutableListOf()),
                get<ListPokemon>(),
                get<SearchPokemon>(),
                kotlinx.coroutines.experimental.IO
        )
    }

    viewModel {
        PokemonDetailsViewModel(
                PokemonModel(),
                kotlinx.coroutines.experimental.IO,
                get<GetPokemon>()
        )
    }

}

val androidUtilModule = module {

    single { TypeColorGenerator() }

}
