package xyz.mcnallydawes.pokedex.screens.pokemondetails

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pokemon_details.*
import org.koin.android.ext.android.inject
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.constants.Extras
import xyz.mcnallydawes.pokedex.common.livedata.NonNullObserver

class PokemonDetailsActivity : AppCompatActivity() {

    private val vm: PokemonDetailsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        setUpObservers()

        val isInitialized = savedInstanceState?.getBoolean(Extras.IS_VIEW_MODEL_INITIALIZED) ?: false
        if (!isInitialized) {
            vm.load(intent?.getStringExtra(Extras.POKEMON_ID) ?: "")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(Extras.IS_VIEW_MODEL_INITIALIZED, true)
        super.onSaveInstanceState(outState)
    }

    private fun setUpObservers() {
        vm.model.observe(this, NonNullObserver(render))
        vm.error.observe(this, NonNullObserver(handleError))
    }

    private val render: (PokemonModel) -> Unit = { model ->
        nameTextView.text = if (model.pokemon != null) {
            model.pokemon.name
        } else {
            getString(R.string.pokemon_not_found)
        }
    }

    private val handleError: (Throwable) -> Unit = { throwable ->
        Snackbar.make(layout, throwable.message ?: "An error occurred", Snackbar.LENGTH_LONG).show()
    }

}