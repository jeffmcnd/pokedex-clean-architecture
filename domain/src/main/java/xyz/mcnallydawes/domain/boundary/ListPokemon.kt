package xyz.mcnallydawes.domain.boundary

import xyz.mcnallydawes.domain.response.ListPokemonResponse
import xyz.mcnallydawes.domain.Try

interface ListPokemon {
    fun execute() : Try<ListPokemonResponse>
}