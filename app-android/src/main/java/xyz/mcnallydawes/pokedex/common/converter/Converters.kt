package xyz.mcnallydawes.pokedex.common.converter

import android.arch.persistence.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import xyz.mcnallydawes.pokedex.entity.Type


class Converters {

    private val setType = Types.newParameterizedType(Set::class.java, Type::class.java)

    private val moshi: JsonAdapter<Set<Type>> = Moshi.Builder()
            .add(TypeTypeAdapter())
            .build()
            .adapter(setType)

    @TypeConverter
    fun jsonToType(json: String): Set<Type> = moshi.fromJson(json) ?: setOf()

    @TypeConverter
    fun typeToJson(types: Set<Type>): String? = moshi.toJson(types)

}