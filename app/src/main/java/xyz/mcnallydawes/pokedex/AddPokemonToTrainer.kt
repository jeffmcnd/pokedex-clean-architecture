package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.AddPokemonToTrainerInteractor
import xyz.mcnallydawes.pokedex.domain.request.AddPokemonToTrainerRequest
import xyz.mcnallydawes.pokedex.domain.response.AddPokemonToTrainerResponse
import xyz.mcnallydawes.pokedex.domain.source.PokemonSource
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class AddPokemonToTrainer(
        private val trainerSource: TrainerSource,
        private val pokemonSource: PokemonSource
) : AddPokemonToTrainerInteractor {

    override fun execute(request: AddPokemonToTrainerRequest): Try<AddPokemonToTrainerResponse> = Try {
        val trainerResponse = trainerSource.read(request.trainerId)

        if (trainerResponse is Failure) {
            throw trainerResponse.throwable
        }

        val pokemonResponse = pokemonSource.read(request.pokemonId)

        if (pokemonResponse is Failure) {
            throw pokemonResponse.throwable
        }

        val trainer = (trainerResponse as Success).value
        val pokemon = (pokemonResponse as Success).value

        if (trainer == null) {
            throw Exception("Trainer with id ${request.trainerId} does not exist.")
        } else if (pokemon == null) {
            throw Exception("Pokemon with id ${request.pokemonId} does not exist.")
        }

        trainer.starters.add(pokemon)

        trainerSource.update(trainer)

        AddPokemonToTrainerResponse(trainer)
    }

}