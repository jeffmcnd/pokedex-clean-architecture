package xyz.mcnallydawes.pokedex.domain.boundary

import xyz.mcnallydawes.pokedex.domain.request.GetPokemonRequest
import xyz.mcnallydawes.pokedex.domain.response.GetPokemonResponse

interface GetPokemonInteractor : Interactor<GetPokemonRequest, GetPokemonResponse>