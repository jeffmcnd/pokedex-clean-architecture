package xyz.mcnallydawes.pokedex.domain.response

import xyz.mcnallydawes.pokedex.domain.entity.Trainer

data class SearchTrainersResponse(val trainers: List<Trainer>)