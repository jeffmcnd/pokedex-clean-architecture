package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.SearchPokemonRequest
import xyz.mcnallydawes.pokedex.response.SearchPokemonResponse

interface SearchPokemon : Interactor<SearchPokemonRequest, SearchPokemonResponse>