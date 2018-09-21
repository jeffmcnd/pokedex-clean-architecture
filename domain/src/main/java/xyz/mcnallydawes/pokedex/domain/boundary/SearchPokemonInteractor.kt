package xyz.mcnallydawes.pokedex.domain.boundary

import xyz.mcnallydawes.pokedex.domain.request.SearchPokemonRequest
import xyz.mcnallydawes.pokedex.domain.response.SearchPokemonResponse

interface SearchPokemonInteractor : Interactor<SearchPokemonRequest, SearchPokemonResponse>