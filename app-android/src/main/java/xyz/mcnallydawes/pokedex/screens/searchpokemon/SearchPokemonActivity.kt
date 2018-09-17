package xyz.mcnallydawes.pokedex.screens.searchpokemon

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_search_pokemon.*
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.adapter.PokemonAdapter
import xyz.mcnallydawes.pokedex.common.constants.Extras
import xyz.mcnallydawes.pokedex.common.inflater.PokemonInflater
import xyz.mcnallydawes.pokedex.common.livedata.NonNullObserver
import xyz.mcnallydawes.pokedex.di.AndroidInjector
import java.util.concurrent.TimeUnit

class SearchPokemonActivity : AppCompatActivity() {

    private val vm: SearchPokemonViewModel by lazy {
        val factory = AndroidInjector.getViewModelFactory(this)
        ViewModelProviders.of(this, factory).get(SearchPokemonViewModel::class.java)
    }

    private val pokemonAdapter: PokemonAdapter by lazy {
        val inflater = PokemonInflater(LayoutInflater.from(this))
        PokemonAdapter(inflater) { pokemon ->
            // TODO: Go to Pokemon details page
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_pokemon)

        setUpViews()
        setUpObservers()

        val isInitialized = savedInstanceState?.getBoolean(Extras.IS_VIEW_MODEL_INITIALIZED) ?: false
        if (!isInitialized) {
            vm.load()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(Extras.IS_VIEW_MODEL_INITIALIZED, true)
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        pokemonList.layoutManager = LinearLayoutManager(this)
        pokemonList.adapter = pokemonAdapter

        clearBtn.setOnClickListener {
            clearBtn.visibility = View.GONE
            searchEditText.setText("", TextView.BufferType.EDITABLE)
            vm.load()
        }

        RxTextView.afterTextChangeEvents(searchEditText)
                .skip(1)
                .filter { searchEditText.text.toString().length >= 2 }
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe {
                    vm.search(searchEditText.text.toString())
                }
    }

    private fun setUpObservers() {
        vm.model.observe(this, NonNullObserver(render))
        vm.error.observe(this, NonNullObserver(handleError))
    }

    private val render: (SearchPokemonModel) -> Unit = { model ->
        pokemonAdapter.replace(model.pokemon)
    }

    private val handleError: (Throwable) -> Unit = { throwable ->
        Snackbar.make(layout, throwable.message ?: "An error occurred", Snackbar.LENGTH_LONG).show()
    }

}