package xyz.mcnallydawes.domain.boundary

import xyz.mcnallydawes.domain.request.GetPokemonRequest
import xyz.mcnallydawes.domain.response.GetPokemonResponse

interface GetPokemon : Interactor<GetPokemonRequest, GetPokemonResponse>