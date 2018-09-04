package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import xyz.mcnallydawes.pokedex.boundary.ListPokemonInteractor
import xyz.mcnallydawes.pokedex.entity.Grass
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.request.ListPokemonRequest
import xyz.mcnallydawes.pokedex.source.PokemonSource

class ListPokemonTest {


    private val pokemonSource: PokemonSource = mock()

    private val listPokemon: ListPokemon = ListPokemon(pokemonSource)

    @Test
    fun `execute fails when pokemon source fails`() {
        val failure = Failure<List<Pokemon>>(Throwable("Random throwable"))
        doReturn(failure).whenever(pokemonSource).getAll()
        val response = listPokemon.execute(ListPokemonRequest())
        assertEquals(response, failure)
    }

    @Test
    fun `execute returns empty list when pokemon source is empty`() {
        val success = Success(ArrayList<Pokemon>())
        doReturn(success).whenever(pokemonSource).getAll()
        val response = listPokemon.execute(ListPokemonRequest()) as Success
        assertEquals(response.value.pokemon, success.value)
    }

    @Test
    fun `execute returns correct list`() {
        val success = Success(listOf(Pokemon("1", "Bulbasaur", setOf(Grass))))
        doReturn(success).whenever(pokemonSource).getAll()
        val response = listPokemon.execute(ListPokemonRequest()) as Success
        assertEquals(response.value.pokemon, success.value)
    }

}