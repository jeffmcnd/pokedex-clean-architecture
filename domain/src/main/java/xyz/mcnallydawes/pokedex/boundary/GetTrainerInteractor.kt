package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.GetTrainerRequest
import xyz.mcnallydawes.pokedex.response.GetTrainerResponse

interface GetTrainerInteractor : Interactor<GetTrainerRequest, GetTrainerResponse>