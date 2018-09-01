package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.GetPokemonRequest
import xyz.mcnallydawes.pokedex.response.GetPokemonResponse

interface GetPokemonInteractor : Interactor<GetPokemonRequest, GetPokemonResponse>