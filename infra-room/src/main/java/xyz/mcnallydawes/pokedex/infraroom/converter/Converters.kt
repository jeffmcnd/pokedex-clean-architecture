package xyz.mcnallydawes.pokedex.infraroom.converter

import android.arch.persistence.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import xyz.mcnallydawes.pokedex.domain.entity.Type


class Converters {

    private val setType = com.squareup.moshi.Types.newParameterizedType(Set::class.java, Type::class.java)

    private val moshi: JsonAdapter<Set<Type>> = Moshi.Builder()
            .add(TypeTypeAdapter())
            .build()
            .adapter(setType)

    @TypeConverter
    fun jsonToType(json: String): Set<Type> = moshi.fromJson(json) ?: setOf()

    @TypeConverter
    fun typeToJson(types: Set<Type>): String? = moshi.toJson(types)

}