package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.ListPokemonInteractor
import xyz.mcnallydawes.pokedex.domain.request.ListPokemonRequest
import xyz.mcnallydawes.pokedex.domain.response.ListPokemonResponse
import xyz.mcnallydawes.pokedex.domain.source.PokemonSource

class ListPokemon(private val pokemonSource: PokemonSource) : ListPokemonInteractor {

    override fun execute(request: ListPokemonRequest): Try<ListPokemonResponse> =
            pokemonSource.getAll()
                    .map { pokemon ->
                        ListPokemonResponse(pokemon)
                    }

}