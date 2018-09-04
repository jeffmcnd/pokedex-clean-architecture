package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.ListTrainersRequest
import xyz.mcnallydawes.pokedex.response.ListTrainersResponse

interface ListTrainersInteractor : Interactor<ListTrainersRequest, ListTrainersResponse>