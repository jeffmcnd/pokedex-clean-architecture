package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.ListPokemonRequest
import xyz.mcnallydawes.pokedex.response.ListPokemonResponse

interface ListPokemon : Interactor<ListPokemonRequest, ListPokemonResponse>
