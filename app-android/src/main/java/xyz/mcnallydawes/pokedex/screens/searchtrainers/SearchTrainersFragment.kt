package xyz.mcnallydawes.pokedex.screens.searchtrainers

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.mcnallydawes.pokedex.R

class SearchTrainersFragment: Fragment() {

    companion object {
        fun newInstance(): SearchTrainersFragment = SearchTrainersFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search_trainers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}