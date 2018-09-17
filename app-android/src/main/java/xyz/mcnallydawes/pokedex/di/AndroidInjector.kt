package xyz.mcnallydawes.pokedex.di

import android.content.Context
import xyz.mcnallydawes.pokedex.GetPokemon
import xyz.mcnallydawes.pokedex.ListPokemon
import xyz.mcnallydawes.pokedex.SearchPokemon
import xyz.mcnallydawes.pokedex.boundary.ListPokemonInteractor
import xyz.mcnallydawes.pokedex.boundary.SearchPokemonInteractor
import xyz.mcnallydawes.pokedex.data.AppDatabase
import xyz.mcnallydawes.pokedex.data.pokemon.PokemonDao
import xyz.mcnallydawes.pokedex.data.pokemon.PokemonMapper
import xyz.mcnallydawes.pokedex.data.pokemon.RoomPokemonSource
import xyz.mcnallydawes.pokedex.source.PokemonSource

object AndroidInjector {

    fun getAppDatabase(context: Context) : AppDatabase = AppDatabase.getInstance(context)

    fun getPokemonDao(
            context: Context,
            appDatabase: AppDatabase = getAppDatabase(context)
    ): PokemonDao = appDatabase.pokemonDao()

    fun getPokemonMapper(): PokemonMapper = PokemonMapper()

    fun getRoomPokemonSource(
            context: Context,
            pokemonDao: PokemonDao = getPokemonDao(context),
            pokemonMapper: PokemonMapper = getPokemonMapper()
    ): RoomPokemonSource = RoomPokemonSource(pokemonDao, pokemonMapper)

    fun getListPokemon(
            context: Context,
            pokemonSource: PokemonSource = getRoomPokemonSource(context)
    ): ListPokemon = ListPokemon(pokemonSource)

    fun getSearchPokemon(
            context: Context,
            pokemonSource: PokemonSource = getRoomPokemonSource(context)
    ): SearchPokemon = SearchPokemon(pokemonSource)

    fun getGetPokemon(
            context: Context,
            pokemonSource: PokemonSource = getRoomPokemonSource(context)
    ) : GetPokemon = GetPokemon(pokemonSource)

    fun getViewModelFactory(
            context: Context,
            listPokemon: ListPokemonInteractor = getListPokemon(context),
            searchPokemon: SearchPokemonInteractor = getSearchPokemon(context),
            getPokemon: GetPokemon = getGetPokemon(context)
    ): ViewModelFactory = ViewModelFactory(
            listPokemon,
            searchPokemon,
            getPokemon
    )

}