package xyz.mcnallydawes.pokedex.common.util

import android.content.res.Resources
import android.support.v4.content.res.ResourcesCompat
import xyz.mcnallydawes.pokedex.R
import xyz.mcnallydawes.pokedex.domain.entity.*

class TypeColorGenerator : (Resources, Type) -> Int {

    override fun invoke(resources: Resources, type: Type): Int = when (type) {
        Grass -> ResourcesCompat.getColor(resources, R.color.grassColor, null)
        Fire -> ResourcesCompat.getColor(resources, R.color.fireColor, null)
        Water -> ResourcesCompat.getColor(resources, R.color.waterColor, null)
        Bug -> ResourcesCompat.getColor(resources, R.color.bugColor, null)
        Normal -> ResourcesCompat.getColor(resources, R.color.normalColor, null)
        Flying -> ResourcesCompat.getColor(resources, R.color.flyingColor, null)
        Poison -> ResourcesCompat.getColor(resources, R.color.poisonColor, null)
    }

}