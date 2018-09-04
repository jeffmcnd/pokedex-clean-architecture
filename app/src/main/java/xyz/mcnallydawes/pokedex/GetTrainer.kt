package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.boundary.GetTrainerInteractor
import xyz.mcnallydawes.pokedex.request.GetTrainerRequest
import xyz.mcnallydawes.pokedex.response.GetTrainerResponse
import xyz.mcnallydawes.pokedex.source.TrainerSource

class GetTrainer(private val trainerSource: TrainerSource) : GetTrainerInteractor {

    override fun execute(request: GetTrainerRequest): Try<GetTrainerResponse> =
            trainerSource.getById(request.id)
                    .flatMap { trainer ->
                        if (trainer == null) {
                            Failure<GetTrainerResponse>(Throwable("Trainer not found"))
                        } else {
                            Success(GetTrainerResponse(trainer))
                        }
                    }

}
