package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.boundary.AddPokemonToTrainerInteractor
import xyz.mcnallydawes.pokedex.request.AddPokemonToTrainerRequest
import xyz.mcnallydawes.pokedex.response.AddPokemonToTrainerResponse
import xyz.mcnallydawes.pokedex.source.PokemonSource
import xyz.mcnallydawes.pokedex.source.TrainerSource

class AddPokemonToTrainer(
        private val trainerSource: TrainerSource,
        private val pokemonSource: PokemonSource
) : AddPokemonToTrainerInteractor {

    override fun execute(request: AddPokemonToTrainerRequest): Try<AddPokemonToTrainerResponse> = Try {
        val trainerResponse = trainerSource.getById(request.trainerId)

        if (trainerResponse is Failure) {
            throw trainerResponse.e
        }

        val pokemonResponse = pokemonSource.getById(request.pokemonId)

        if (pokemonResponse is Failure) {
            throw pokemonResponse.e
        }

        val trainer = (trainerResponse as Success).value
        val pokemon = (pokemonResponse as Success).value

        if (trainer == null) {
            throw Exception("Trainer with id ${request.trainerId} does not exist.")
        } else if (pokemon == null) {
            throw Exception("Pokemon with id ${request.pokemonId} does not exist.")
        }

        trainer.starters.add(pokemon)

        trainerSource.save(trainer)

        AddPokemonToTrainerResponse(trainer)
    }

}