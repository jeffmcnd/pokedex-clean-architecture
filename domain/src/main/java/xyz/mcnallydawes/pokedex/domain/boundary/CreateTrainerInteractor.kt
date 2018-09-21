package xyz.mcnallydawes.pokedex.domain.boundary

import xyz.mcnallydawes.pokedex.domain.request.CreateTrainerRequest
import xyz.mcnallydawes.pokedex.domain.response.CreateTrainerResponse

interface CreateTrainerInteractor : Interactor<CreateTrainerRequest, CreateTrainerResponse>