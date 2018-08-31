package xyz.mcnallydawes.domain.response

data class PokemonResponse(
        val id: String,
        val name: String,
        val types: Set<TypeResponse>
)