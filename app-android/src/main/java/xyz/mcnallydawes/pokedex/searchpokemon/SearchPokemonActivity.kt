package xyz.mcnallydawes.pokedex.searchpokemon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_search_pokemon.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import xyz.mcnallydawes.pokedex.Failure
import xyz.mcnallydawes.pokedex.ListPokemon
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.Success
import xyz.mcnallydawes.pokedex.common.adapter.PokemonAdapter
import xyz.mcnallydawes.pokedex.data.AppDatabase
import xyz.mcnallydawes.pokedex.data.pokemon.PokemonMapper
import xyz.mcnallydawes.pokedex.data.pokemon.RoomPokemonSource
import xyz.mcnallydawes.pokedex.request.ListPokemonRequest

class SearchPokemonActivity : AppCompatActivity() {

    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_pokemon)
        setUpViews()
        start()
    }

    private fun setUpViews() {
        pokemonList.layoutManager = LinearLayoutManager(this)
        pokemonAdapter = PokemonAdapter(LayoutInflater.from(this))
        pokemonList.adapter = pokemonAdapter
    }

    private fun start() {

        val pokemonDao = AppDatabase.getInstance(this).pokemonDao()
        val pokemonMapper = PokemonMapper()
        val pokemonSource = RoomPokemonSource(pokemonDao, pokemonMapper)
        val listPokemon = ListPokemon(pokemonSource)

        launch(UI) {
            val response = listPokemon.execute(ListPokemonRequest())
            when (response) {
                is Success -> pokemonAdapter.addPokemon(response.value.pokemon)
                is Failure -> TODO("Implement")
            }
        }
    }

}