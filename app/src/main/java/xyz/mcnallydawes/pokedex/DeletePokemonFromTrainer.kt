package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.DeletePokemonFromTrainerInteractor
import xyz.mcnallydawes.pokedex.domain.request.DeletePokemonFromTrainerRequest
import xyz.mcnallydawes.pokedex.domain.response.DeletePokemonFromTrainerResponse
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class DeletePokemonFromTrainer(
        private val trainerSource: TrainerSource
) : DeletePokemonFromTrainerInteractor {

    override fun execute(request: DeletePokemonFromTrainerRequest): Try<DeletePokemonFromTrainerResponse> = Try {
        val trainerResponse = trainerSource.read(request.trainerId)

        if (trainerResponse is Failure) {
            throw trainerResponse.throwable
        }

        val trainer = (trainerResponse as Success).value ?: throw Throwable("Trainer with id ${request.trainerId} does not exist.")

        var position = -1
        trainer.starters.forEachIndexed { index, pokemon ->
            if (pokemon.id == request.pokemonId) {
                position = index
            }
        }

        if (position >= 0) {
            trainer.starters.removeAt(position)
        }

        trainerSource.update(trainer)
        DeletePokemonFromTrainerResponse(trainer)
    }

}