package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.boundary.ListPokemonInteractor
import xyz.mcnallydawes.pokedex.request.ListPokemonRequest
import xyz.mcnallydawes.pokedex.response.ListPokemonResponse
import xyz.mcnallydawes.pokedex.source.PokemonSource

class ListPokemon(private val pokemonSource: PokemonSource) : ListPokemonInteractor {

    override fun execute(request: ListPokemonRequest): Try<ListPokemonResponse> =
            pokemonSource.getAll()
                    .map { pokemon ->
                        ListPokemonResponse(pokemon)
                    }

}