package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.boundary.SearchPokemonInteractor
import xyz.mcnallydawes.pokedex.request.SearchPokemonRequest
import xyz.mcnallydawes.pokedex.response.SearchPokemonResponse
import xyz.mcnallydawes.pokedex.source.PokemonSource

class SearchPokemon(private val pokemonSource: PokemonSource) : SearchPokemonInteractor {

    override fun execute(request: SearchPokemonRequest): Try<SearchPokemonResponse> =
            pokemonSource.getByNameStartingWith(request.query)
                    .map { pokemon ->
                        SearchPokemonResponse(pokemon)
                    }

}