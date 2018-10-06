package xyz.mcnallydawes.pokedex.common.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_trainer.view.*
import xyz.mcnallydawes.pokedex.common.adapter.TrainerClickListener
import xyz.mcnallydawes.pokedex.domain.entity.Trainer

class TrainerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(trainer: Trainer, listener: TrainerClickListener?) = with(itemView) {
        nameTv.text = trainer.name
        setOnClickListener {
            listener?.invoke(trainer)
        }
    }

}