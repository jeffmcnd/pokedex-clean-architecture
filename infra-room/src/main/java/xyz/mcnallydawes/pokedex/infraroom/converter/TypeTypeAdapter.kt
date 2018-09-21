package xyz.mcnallydawes.pokedex.infraroom.converter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import xyz.mcnallydawes.pokedex.domain.entity.Fire
import xyz.mcnallydawes.pokedex.domain.entity.Grass
import xyz.mcnallydawes.pokedex.domain.entity.Type
import xyz.mcnallydawes.pokedex.domain.entity.Water

class TypeTypeAdapter {

    @ToJson
    fun toJson(type: Type) : String = type.id

    @FromJson
    fun fromJson(json: String): Type = when (json) {
        "1" -> Grass
        "2" -> Fire
        "3" -> Water
        else -> Grass
    }

}