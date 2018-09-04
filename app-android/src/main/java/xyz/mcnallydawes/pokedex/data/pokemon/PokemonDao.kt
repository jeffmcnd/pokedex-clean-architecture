package xyz.mcnallydawes.pokedex.data.pokemon

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg pokemon: RoomPokemon)

    @Query("select * from pokemon where id = :id")
    fun getById(id: String): RoomPokemon?

    @Query("select * from pokemon")
    fun getAll() : List<RoomPokemon>

    @Query("select * from pokemon where LOWER(name) like LOWER(:name)")
    fun getByNameStartingWith(name: String): List<RoomPokemon>

}
