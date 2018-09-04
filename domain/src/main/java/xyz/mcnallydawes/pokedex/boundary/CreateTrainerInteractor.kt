package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.CreateTrainerRequest
import xyz.mcnallydawes.pokedex.response.CreateTrainerResponse

interface CreateTrainerInteractor : Interactor<CreateTrainerRequest, CreateTrainerResponse>