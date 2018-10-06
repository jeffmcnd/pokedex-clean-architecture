package xyz.mcnallydawes.pokedex.screens.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel(private var mainModel: MainModel) : ViewModel() {

    private val _model: MutableLiveData<MainModel> = MutableLiveData()
    val model: LiveData<MainModel> = _model

    fun onTrainersBtnClicked() {
        _model.value = this.mainModel.copy(page = SearchTrainersPage)
    }

    fun onPokemonBtnClicked() {
        _model.value = this.mainModel.copy(page = SearchPokemonPage)
    }

}