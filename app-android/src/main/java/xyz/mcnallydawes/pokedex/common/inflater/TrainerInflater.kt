package xyz.mcnallydawes.pokedex.common.inflater

import android.view.LayoutInflater
import android.view.ViewGroup
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.common.viewholder.TrainerViewHolder

class TrainerInflater(private val layoutInflater: LayoutInflater) {

    fun inflate(parent: ViewGroup, viewType: Int) =
            TrainerViewHolder(layoutInflater.inflate(R.layout.item_trainer, parent, false))

}