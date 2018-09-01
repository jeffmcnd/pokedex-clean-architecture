package xyz.mcnallydawes.domain.boundary

import xyz.mcnallydawes.domain.request.ListPokemonRequest
import xyz.mcnallydawes.domain.response.ListPokemonResponse

interface ListPokemon : Interactor<ListPokemonRequest, ListPokemonResponse>
