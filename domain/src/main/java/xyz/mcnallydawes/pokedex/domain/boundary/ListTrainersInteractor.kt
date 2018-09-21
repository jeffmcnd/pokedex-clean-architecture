package xyz.mcnallydawes.pokedex.domain.boundary

import xyz.mcnallydawes.pokedex.domain.request.ListTrainersRequest
import xyz.mcnallydawes.pokedex.domain.response.ListTrainersResponse

interface ListTrainersInteractor : Interactor<ListTrainersRequest, ListTrainersResponse>