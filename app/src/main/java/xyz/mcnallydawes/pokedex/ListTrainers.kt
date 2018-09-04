package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.boundary.ListTrainersInteractor
import xyz.mcnallydawes.pokedex.request.ListTrainersRequest
import xyz.mcnallydawes.pokedex.response.ListTrainersResponse
import xyz.mcnallydawes.pokedex.source.TrainerSource

class ListTrainers(private val trainerSource: TrainerSource) : ListTrainersInteractor {

    override fun execute(request: ListTrainersRequest): Try<ListTrainersResponse> =
            trainerSource.getAll()
                    .map { trainers ->
                        ListTrainersResponse(trainers)
                    }

}