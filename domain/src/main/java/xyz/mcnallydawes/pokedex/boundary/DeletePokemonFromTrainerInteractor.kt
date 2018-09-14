package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.DeletePokemonFromTrainerRequest
import xyz.mcnallydawes.pokedex.response.DeletePokemonFromTrainerResponse

interface DeletePokemonFromTrainerInteractor : Interactor<DeletePokemonFromTrainerRequest, DeletePokemonFromTrainerResponse>