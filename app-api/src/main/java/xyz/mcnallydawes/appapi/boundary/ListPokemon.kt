package xyz.mcnallydawes.appapi.boundary

import xyz.mcnallydawes.appapi.response.ListPokemonResponse
import xyz.mcnallydawes.domain.Try

interface ListPokemon {
    fun execute() : Try<ListPokemonResponse>
}