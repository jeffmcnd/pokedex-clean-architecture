package xyz.mcnallydawes.pokedex.screens.searchtrainers

import xyz.mcnallydawes.pokedex.domain.entity.Trainer

data class SearchTrainersModel(var lastQuery: String, var trainers: MutableList<Trainer>)