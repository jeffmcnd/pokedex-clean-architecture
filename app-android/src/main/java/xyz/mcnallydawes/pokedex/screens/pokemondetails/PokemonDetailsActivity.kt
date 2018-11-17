package xyz.mcnallydawes.pokedex.screens.pokemondetails

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pokemon_details.*
import org.koin.android.ext.android.inject
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.util.TypeColorGenerator
import xyz.mcnallydawes.pokedex.common.constants.Extras
import xyz.mcnallydawes.pokedex.common.livedata.NonNullObserver

class PokemonDetailsActivity : AppCompatActivity() {

    private val vm: PokemonDetailsViewModel by inject()
    private val typeColorGenerator: TypeColorGenerator by inject()

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setContentView(R.layout.activity_pokemon_details)
        setUpViews()
        setUpViewModel(savedState)
        setUpObservers()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(Extras.IS_VIEW_MODEL_INITIALIZED, true)
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpViewModel(savedState: Bundle?) {
        val isInitialized = savedState?.getBoolean(Extras.IS_VIEW_MODEL_INITIALIZED) ?: false
        if (!isInitialized) {
            vm.load(intent?.getStringExtra(Extras.POKEMON_ID) ?: "")
        }
    }

    private fun setUpObservers() {
        vm.model.observe(this, NonNullObserver(render))
        vm.error.observe(this, NonNullObserver(handleError))
    }

    private val render: (PokemonModel) -> Unit = { model ->
        val pokemon = model.pokemon
        if (pokemon != null) {
            toolbar.title = pokemon.name

            imageView.setBackgroundColor(ContextCompat.getColor(this, R.color.separatorColor))

            val params = LinearLayout.LayoutParams(MATCH_PARENT, 0, 1F)
            pokemon.types.forEach { type ->
                TextView(this).apply {
                    gravity = Gravity.CENTER
                    layoutParams = params
                    setBackgroundColor(typeColorGenerator(resources, type))
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                    text = type.name
                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    textSize = 18F
                }.also {
                    typeContainer.addView(it)
                }
            }
        } else {
            toolbar.title = getString(R.string.pokemon_not_found)
            imageView.setBackgroundResource(R.color.black)
            headerLinearLayout.setBackgroundResource(R.color.black)
        }
    }

    private val handleError: (Throwable) -> Unit = { throwable ->
        Snackbar.make(layout, throwable.message ?: "An error occurred", Snackbar.LENGTH_LONG).show()
    }

}