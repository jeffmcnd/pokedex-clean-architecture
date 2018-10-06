package xyz.mcnallydawes.pokedex.screens.searchtrainers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.launch
import xyz.mcnallydawes.pokedex.Failure
import xyz.mcnallydawes.pokedex.Success
import xyz.mcnallydawes.pokedex.domain.boundary.ListTrainersInteractor
import xyz.mcnallydawes.pokedex.domain.boundary.SearchTrainersInteractor
import xyz.mcnallydawes.pokedex.domain.request.ListTrainersRequest
import xyz.mcnallydawes.pokedex.domain.request.SearchTrainersRequest
import kotlin.coroutines.experimental.CoroutineContext

class SearchTrainersViewModel(
        private val searchTrainersModel: SearchTrainersModel,
        private val listTrainers: ListTrainersInteractor,
        private val searchTrainers: SearchTrainersInteractor,
        private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _model: MutableLiveData<SearchTrainersModel> = MutableLiveData()
    val model: LiveData<SearchTrainersModel> = _model

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error

    fun load() {
        launch(coroutineContext) {
            val response = listTrainers.execute(ListTrainersRequest())
            when (response) {
                is Success -> {
                    searchTrainersModel.trainers = response.value.trainers.toMutableList()
                    _model.postValue(searchTrainersModel)
                }
                is Failure -> _error.postValue(response.throwable)
            }
        }
    }

    fun search(query: String) {
        launch(coroutineContext) {
            val response = searchTrainers.execute(SearchTrainersRequest(query))
            when (response) {
                is Success -> {
                    val trainers = response.value.trainers
                    if (searchTrainersModel.lastQuery == query) {
                        searchTrainersModel.trainers.addAll(trainers)
                    } else {
                        searchTrainersModel.trainers = trainers.toMutableList()
                    }
                    _model.postValue(searchTrainersModel)
                }
                is Failure -> _error.postValue(response.throwable)
            }
        }
    }

}