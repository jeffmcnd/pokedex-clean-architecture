package xyz.mcnallydawes.appapi.response

data class PokemonResponse(
        val id: String,
        val name: String,
        val types: Set<TypeResponse>
)