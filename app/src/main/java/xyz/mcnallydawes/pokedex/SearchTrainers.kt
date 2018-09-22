package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.domain.boundary.SearchTrainersInteractor
import xyz.mcnallydawes.pokedex.domain.request.SearchTrainersRequest
import xyz.mcnallydawes.pokedex.domain.response.SearchTrainersResponse
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class SearchTrainers(private val trainerSource: TrainerSource) : SearchTrainersInteractor {

    override fun execute(request: SearchTrainersRequest): Try<SearchTrainersResponse> =
            trainerSource.search(request.query)
                    .map { trainers ->
                        SearchTrainersResponse(trainers)
                    }

}