package xyz.mcnallydawes.pokedex.domain.boundary

import xyz.mcnallydawes.pokedex.domain.request.ListPokemonRequest
import xyz.mcnallydawes.pokedex.domain.response.ListPokemonResponse

interface ListPokemonInteractor : Interactor<ListPokemonRequest, ListPokemonResponse>
