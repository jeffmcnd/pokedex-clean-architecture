package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.SearchPokemonRequest
import xyz.mcnallydawes.pokedex.response.SearchPokemonResponse

interface SearchPokemonInteractor : Interactor<SearchPokemonRequest, SearchPokemonResponse>