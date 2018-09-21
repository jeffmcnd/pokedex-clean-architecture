package xyz.mcnallydawes.pokedex.infraroom

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import xyz.mcnallydawes.pokedex.infraroom.converter.Converters
import xyz.mcnallydawes.pokedex.infraroom.dao.PokemonDao
import xyz.mcnallydawes.pokedex.infraroom.entity.RoomPokemon

@Database(entities = [RoomPokemon::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
