package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import xyz.mcnallydawes.pokedex.boundary.GetPokemonInteractor
import xyz.mcnallydawes.pokedex.entity.Grass
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.request.GetPokemonRequest
import xyz.mcnallydawes.pokedex.source.PokemonSource

class GetPokemonTest {

    private lateinit var getPokemon: GetPokemonInteractor

    private val pokemonSource: PokemonSource = mock()

    @Before
    fun setUp() {
        getPokemon = GetPokemon(pokemonSource)
    }

    @Test
    fun `execute fails when pokemon not found`() {
        val success = Success(null)
        whenever(pokemonSource.getById("1")).thenReturn(success)
        val response = getPokemon.execute(GetPokemonRequest("1"))
        assertThat(response, instanceOf(Failure::class.java))
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