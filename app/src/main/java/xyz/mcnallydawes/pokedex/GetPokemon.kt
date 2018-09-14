package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.boundary.GetPokemonInteractor
import xyz.mcnallydawes.pokedex.request.GetPokemonRequest
import xyz.mcnallydawes.pokedex.response.GetPokemonResponse
import xyz.mcnallydawes.pokedex.source.PokemonSource

class GetPokemon(private val pokemonSource: PokemonSource) : GetPokemonInteractor {

// Test for Jenkins 3

    override fun execute(request: GetPokemonRequest): Try<GetPokemonResponse> =
            pokemonSource.getById(request.id)
                    .flatMap { pokemon ->
                        if (pokemon == null) {
                            Failure<GetPokemonResponse>(Throwable("Pokemon not found"))
                        } else {
                            Success(GetPokemonResponse(pokemon))
                        }
                    }

}
