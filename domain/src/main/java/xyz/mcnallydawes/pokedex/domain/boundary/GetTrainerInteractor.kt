package xyz.mcnallydawes.pokedex.domain.boundary

import xyz.mcnallydawes.pokedex.domain.request.GetTrainerRequest
import xyz.mcnallydawes.pokedex.domain.response.GetTrainerResponse

interface GetTrainerInteractor : Interactor<GetTrainerRequest, GetTrainerResponse>