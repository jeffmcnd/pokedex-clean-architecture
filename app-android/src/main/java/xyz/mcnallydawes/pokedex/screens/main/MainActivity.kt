package xyz.mcnallydawes.pokedex.screens.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.livedata.NonNullObserver
import xyz.mcnallydawes.pokedex.screens.searchpokemon.SearchPokemonFragment
import xyz.mcnallydawes.pokedex.screens.searchtrainers.SearchTrainersFragment

class MainActivity : AppCompatActivity() {

    private val searchTrainersTag = "tag_search_trainers"
    private val searchPokemonTag = "tag_search_pokemon"

    private val vm: MainViewModel by inject()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.nav_search_trainers -> {
                vm.onTrainersBtnClicked()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_search_pokemon -> {
                vm.onPokemonBtnClicked()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val searchTrainersFragment: SearchTrainersFragment by lazy {
        SearchTrainersFragment.newInstance()
    }

    private val searchPokemonFragment: SearchPokemonFragment by lazy {
        SearchPokemonFragment.newInstance()
    }

    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        currentFragment = searchTrainersFragment
        showSearchTrainers()
    }

    private fun setUpObservers() {
        vm.model.observe(this, NonNullObserver(render))
    }

    private val render: (MainModel) -> Unit = { model ->
        when (model.page) {
            is SearchTrainersPage -> showSearchTrainers()
            is SearchPokemonPage -> showSearchPokemon()
        }
    }

    private fun showSearchTrainers() = showFragment(searchTrainersTag, searchTrainersFragment)

    private fun showSearchPokemon() = showFragment(searchPokemonTag, searchPokemonFragment)

    private fun showFragment(tag: String, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            transaction.add(R.id.container, fragment, tag)
        }

        transaction.hide(currentFragment)
        transaction.show(fragment)
        transaction.commit()

        currentFragment = fragment
    }

}