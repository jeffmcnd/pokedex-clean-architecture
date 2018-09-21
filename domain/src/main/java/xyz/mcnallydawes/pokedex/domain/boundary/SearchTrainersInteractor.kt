package xyz.mcnallydawes.pokedex.domain.boundary

import xyz.mcnallydawes.pokedex.domain.request.SearchTrainersRequest
import xyz.mcnallydawes.pokedex.domain.response.SearchTrainersResponse

interface SearchTrainersInteractor : Interactor<SearchTrainersRequest, SearchTrainersResponse>