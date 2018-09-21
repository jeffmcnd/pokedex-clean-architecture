package xyz.mcnallydawes.pokedex

import android.app.Application
import org.koin.android.ext.android.startKoin
import xyz.mcnallydawes.pokedex.infrainmemory.inMemoryModule
import xyz.mcnallydawes.pokedex.infraroom.roomModule

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
                this,
                listOf(
                        inMemoryModule,
                        roomModule,
                        interactorModule,
                        viewModelModule
                )
        )
    }

}