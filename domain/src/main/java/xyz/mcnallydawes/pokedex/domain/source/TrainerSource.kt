package xyz.mcnallydawes.pokedex.domain.source

import xyz.mcnallydawes.pokedex.domain.entity.Trainer

interface TrainerSource : CrudSource<Trainer>, SearchSource<Trainer>
