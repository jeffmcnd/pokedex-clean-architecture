package xyz.mcnallydawes.pokedex.infraroom.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import xyz.mcnallydawes.pokedex.infraroom.entity.RoomPokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg pokemon: RoomPokemon)

    @Query("select * from pokemon where id = :id")
    fun read(id: String): RoomPokemon?

    @Query("select * from pokemon")
    fun read() : List<RoomPokemon>

    @Query("select * from pokemon where name like :name")
    fun search(name: String): List<RoomPokemon>

}
