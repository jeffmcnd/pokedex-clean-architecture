package xyz.mcnallydawes.pokedex.domain.source

import xyz.mcnallydawes.pokedex.domain.entity.Pokemon

interface PokemonSource : ReadSource<Pokemon>, SearchSource<Pokemon>
