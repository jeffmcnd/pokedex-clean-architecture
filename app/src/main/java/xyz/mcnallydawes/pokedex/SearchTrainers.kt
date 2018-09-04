package xyz.mcnallydawes.pokedex

import xyz.mcnallydawes.pokedex.boundary.SearchTrainersInteractor
import xyz.mcnallydawes.pokedex.request.SearchTrainersRequest
import xyz.mcnallydawes.pokedex.response.SearchTrainersResponse
import xyz.mcnallydawes.pokedex.source.TrainerSource

class SearchTrainers(private val trainerSource: TrainerSource) : SearchTrainersInteractor {

    override fun execute(request: SearchTrainersRequest): Try<SearchTrainersResponse> =
            trainerSource.getByNameStartingWith(request.query)
                    .map { trainers ->
                        SearchTrainersResponse(trainers)
                    }

}