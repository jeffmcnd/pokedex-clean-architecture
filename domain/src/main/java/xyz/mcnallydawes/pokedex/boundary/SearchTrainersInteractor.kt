package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.request.SearchTrainersRequest
import xyz.mcnallydawes.pokedex.response.SearchTrainersResponse

interface SearchTrainersInteractor : Interactor<SearchTrainersRequest, SearchTrainersResponse>