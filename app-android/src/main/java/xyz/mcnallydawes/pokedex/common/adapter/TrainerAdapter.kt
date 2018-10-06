package xyz.mcnallydawes.pokedex.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import xyz.mcnallydawes.pokedex.common.inflater.TrainerInflater
import xyz.mcnallydawes.pokedex.common.viewholder.TrainerViewHolder
import xyz.mcnallydawes.pokedex.domain.entity.Trainer

typealias TrainerClickListener = (Trainer) -> Unit

class TrainerAdapter(
        private val inflater: TrainerInflater,
        private val listener: TrainerClickListener? = null
) : RecyclerView.Adapter<TrainerViewHolder>() {

    private val trainers: ArrayList<Trainer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainerViewHolder =
            inflater.inflate(parent, viewType)

    override fun getItemCount(): Int = trainers.size

    override fun onBindViewHolder(holder: TrainerViewHolder, position: Int) {
        holder.bind(trainers[position], listener)
    }

    fun replace(trainer: List<Trainer>) {
        this.trainers.clear()
        this.trainers.addAll(trainer)
        notifyDataSetChanged()
    }

}