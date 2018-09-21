package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.SearchPokemonInteractor
import xyz.mcnallydawes.pokedex.domain.request.SearchPokemonRequest
import xyz.mcnallydawes.pokedex.domain.response.SearchPokemonResponse
import xyz.mcnallydawes.pokedex.domain.source.PokemonSource

class SearchPokemon(private val pokemonSource: PokemonSource) : SearchPokemonInteractor {

    override fun execute(request: SearchPokemonRequest): Try<SearchPokemonResponse> =
            pokemonSource.getByNameStartingWith(request.query)
                    .map { pokemon ->
                        SearchPokemonResponse(pokemon)
                    }

}