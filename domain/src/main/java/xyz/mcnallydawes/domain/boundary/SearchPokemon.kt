package xyz.mcnallydawes.domain.boundary

import xyz.mcnallydawes.domain.request.SearchPokemonRequest
import xyz.mcnallydawes.domain.response.SearchPokemonResponse

interface SearchPokemon : Interactor<SearchPokemonRequest, SearchPokemonResponse>