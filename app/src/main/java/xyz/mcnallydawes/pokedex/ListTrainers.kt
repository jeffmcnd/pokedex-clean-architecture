package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.ListTrainersInteractor
import xyz.mcnallydawes.pokedex.domain.request.ListTrainersRequest
import xyz.mcnallydawes.pokedex.domain.response.ListTrainersResponse
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class ListTrainers(private val trainerSource: TrainerSource) : ListTrainersInteractor {

    override fun execute(request: ListTrainersRequest): Try<ListTrainersResponse> =
            trainerSource.getAll()
                    .map { trainers ->
                        ListTrainersResponse(trainers)
                    }

}