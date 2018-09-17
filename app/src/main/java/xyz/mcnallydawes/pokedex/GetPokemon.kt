package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.boundary.GetPokemonInteractor
import xyz.mcnallydawes.pokedex.request.GetPokemonRequest
import xyz.mcnallydawes.pokedex.response.GetPokemonResponse
import xyz.mcnallydawes.pokedex.source.PokemonSource

class GetPokemon(private val pokemonSource: PokemonSource) : GetPokemonInteractor {

    override fun execute(request: GetPokemonRequest): Try<GetPokemonResponse> =
            pokemonSource.getById(request.id)
                    .flatMap { pokemon ->
                        Success(GetPokemonResponse(pokemon))
                    }

}