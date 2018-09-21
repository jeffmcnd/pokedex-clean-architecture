package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.GetPokemonInteractor
import xyz.mcnallydawes.pokedex.domain.request.GetPokemonRequest
import xyz.mcnallydawes.pokedex.domain.response.GetPokemonResponse
import xyz.mcnallydawes.pokedex.domain.source.PokemonSource

class GetPokemon(private val pokemonSource: PokemonSource) : GetPokemonInteractor {

    override fun execute(request: GetPokemonRequest): Try<GetPokemonResponse> =
            pokemonSource.getById(request.id)
                    .flatMap { pokemon ->
                        Success(GetPokemonResponse(pokemon))
                    }

}