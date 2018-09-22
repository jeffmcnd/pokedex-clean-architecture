package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.GetTrainerInteractor
import xyz.mcnallydawes.pokedex.domain.request.GetTrainerRequest
import xyz.mcnallydawes.pokedex.domain.response.GetTrainerResponse
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class GetTrainer(private val trainerSource: TrainerSource) : GetTrainerInteractor {

    override fun execute(request: GetTrainerRequest): Try<GetTrainerResponse> =
            trainerSource.read(request.id)
                    .flatMap { trainer ->
                        if (trainer == null) {
                            Failure<GetTrainerResponse>(Throwable("Trainer not found"))
                        } else {
                            Success(GetTrainerResponse(trainer))
                        }
                    }

}
