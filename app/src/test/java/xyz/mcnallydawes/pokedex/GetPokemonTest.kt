package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import xyz.mcnallydawes.pokedex.domain.entity.Grass
import xyz.mcnallydawes.pokedex.domain.entity.Pokemon
import xyz.mcnallydawes.pokedex.domain.request.GetPokemonRequest
import xyz.mcnallydawes.pokedex.domain.source.PokemonSource

class GetPokemonTest {

    private val pokemonSource: PokemonSource = mock()

    private val getPokemon: GetPokemon = GetPokemon(pokemonSource)

    @Test
    fun `execute success when pokemon not found`() {
        val success = Success(null)
        whenever(pokemonSource.getById("1")).thenReturn(success)
        val response = getPokemon.execute(GetPokemonRequest("1")) as Success
        assertNull(response.value.pokemon)
    }

    @Test
    fun `execute fails when pokemon source fails`() {
        val failure = Failure<Pokemon?>(Throwable("Random failure"))
        whenever(pokemonSource.getById("1")).thenReturn(failure)
        val response = getPokemon.execute(GetPokemonRequest("1"))
        assertEquals(response, failure)
    }

    @Test
    fun `execute success when pokemon found`() {
        val pokemon = Pokemon("1", "Bulbasaur", setOf(Grass))
        val success = Success(pokemon)
        whenever(pokemonSource.getById("1")).thenReturn(success)
        val response = getPokemon.execute(GetPokemonRequest("1")) as Success
        assertEquals(response.value.pokemon, pokemon)
    }

}