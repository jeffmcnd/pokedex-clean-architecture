package xyz.mcnallydawes.pokedex.infraroom.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.ColumnInfo.NOCASE
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import xyz.mcnallydawes.pokedex.domain.entity.Type

@Entity(tableName = "pokemon")
data class RoomPokemon(

        @PrimaryKey @ColumnInfo(name = "id")
        val id: String,

        @ColumnInfo(name = "name", collate = NOCASE)
        val name: String,

        @ColumnInfo(name = "types")
        val types: Set<Type>

)