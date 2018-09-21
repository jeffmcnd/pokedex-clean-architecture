package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.CreateTrainerInteractor
import xyz.mcnallydawes.pokedex.domain.entity.Trainer
import xyz.mcnallydawes.pokedex.domain.request.CreateTrainerRequest
import xyz.mcnallydawes.pokedex.domain.response.CreateTrainerResponse
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class CreateTrainer(private val trainerSource: TrainerSource) : CreateTrainerInteractor {

    override fun execute(request: CreateTrainerRequest): Try<CreateTrainerResponse> = Try {
        val response = trainerSource.getByName(request.name)

        if (response is Failure) {
            throw response.throwable
        }

        if ((response as Success).value != null) {
            throw Throwable("Trainer with name ${request.name} already exists.")
        }

        val newTrainer = Trainer(name = request.name)

        val saveResponse = trainerSource.save(newTrainer)

        if (saveResponse is Failure) throw saveResponse.throwable

        val id = (saveResponse as Success).value

        CreateTrainerResponse(newTrainer.copy(id = id))
    }

}