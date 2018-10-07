package xyz.mcnallydawes.pokedex.screens.searchpokemon

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent
import kotlinx.android.synthetic.main.fragment_search_pokemon.*
import org.koin.android.ext.android.inject
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.TypeColorGenerator
import xyz.mcnallydawes.pokedex.common.adapter.PokemonAdapter
import xyz.mcnallydawes.pokedex.common.constants.Extras
import xyz.mcnallydawes.pokedex.common.inflater.PokemonInflater
import xyz.mcnallydawes.pokedex.common.livedata.NonNullObserver
import xyz.mcnallydawes.pokedex.screens.pokemondetails.PokemonDetailsActivity
import java.util.concurrent.TimeUnit

class SearchPokemonFragment : Fragment() {

    companion object {
        fun newInstance(): SearchPokemonFragment = SearchPokemonFragment()
    }

    private val vm: SearchPokemonViewModel by inject()
    private val typeColorGenerator: TypeColorGenerator by inject()

    private val pokemonAdapter: PokemonAdapter by lazy {
        val inflater = PokemonInflater(LayoutInflater.from(requireContext()), typeColorGenerator)
        PokemonAdapter(inflater) { pokemon ->
            val intent = Intent(requireContext(), PokemonDetailsActivity::class.java)
            intent.putExtra(Extras.POKEMON_ID, pokemon.id)
            startActivity(intent)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search_pokemon, container, false)

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        setUpViews()
        setUpObservers()
        setUpViewModel(savedState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(Extras.IS_VIEW_MODEL_INITIALIZED, true)
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        setUpPokemonRecyclerView()
        setUpClearBtn()
        setUpSearchEditText()
    }

    private fun setUpObservers() {
        vm.model.observe(this, NonNullObserver(render))
        vm.error.observe(this, NonNullObserver(handleError))
    }

    private fun setUpViewModel(savedState: Bundle?) {
        val isInitialized = savedState?.getBoolean(Extras.IS_VIEW_MODEL_INITIALIZED) ?: false
        if (!isInitialized) {
            vm.load()
        }
    }

    private fun setUpPokemonRecyclerView() {
        pokemonList.layoutManager = LinearLayoutManager(requireContext())
        pokemonList.adapter = pokemonAdapter
    }

    private fun setUpClearBtn() {
        clearBtn.visibility = View.GONE
        clearBtn.setOnClickListener {
            clearBtn.visibility = View.GONE
            searchEditText.setText("", TextView.BufferType.EDITABLE)
            vm.load()
        }
    }

    private fun setUpSearchEditText() {
        RxTextView.afterTextChangeEvents(searchEditText)
                .skip(1)
                .doOnNext(updateClearBtnVisibility)
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(handleQuery, {})
    }

    private val updateClearBtnVisibility: (TextViewAfterTextChangeEvent) -> Unit = { event ->
        val query = event.view().text
        clearBtn.visibility = if (query.isNotEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private val handleQuery: (TextViewAfterTextChangeEvent) -> Unit = { event ->
        val query = event.view().text.toString()
        if (query.isEmpty()) {
            vm.load()
        } else if (query.length >= 2) {
            vm.search(searchEditText.text.toString())
        }
    }

    private val render: (SearchPokemonModel) -> Unit = { model ->
        pokemonAdapter.replace(model.pokemon)
    }

    private val handleError: (Throwable) -> Unit = { throwable ->
        Snackbar.make(layout, throwable.message ?: "An error occurred", Snackbar.LENGTH_LONG).show()
    }

}