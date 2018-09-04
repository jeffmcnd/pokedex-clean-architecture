package xyz.mcnallydawes.pokedex.response

import xyz.mcnallydawes.pokedex.entity.Trainer

data class SearchTrainersResponse(val trainers: List<Trainer>)