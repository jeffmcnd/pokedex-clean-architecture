package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import xyz.mcnallydawes.pokedex.boundary.SearchPokemonInteractor
import xyz.mcnallydawes.pokedex.entity.Fire
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.request.SearchPokemonRequest
import xyz.mcnallydawes.pokedex.source.PokemonSource

class SearchPokemonTest {

    private val pokemonSource: PokemonSource = mock()

    private val searchPokemon: SearchPokemonInteractor = SearchPokemon(pokemonSource)

    @Test
    fun `execute fails when pokemon source fails`() {
        val query = "char"
        val failure = Failure<List<Pokemon>>(Throwable("Random throwable"))
        doReturn(failure).whenever(pokemonSource).getByNameStartingWith(query)
        val response = searchPokemon.execute(SearchPokemonRequest(query))
        assertEquals(response, failure)
    }

    @Test
    fun `execute returns empty list when no pokemon are found`() {
        val query = "non-existing-pokemon-search"
        val success = Success<List<Pokemon>>(emptyList())
        doReturn(success).whenever(pokemonSource).getByNameStartingWith(query)
        val response = searchPokemon.execute(SearchPokemonRequest(query)) as Success
        assertTrue(response.value.pokemon.isEmpty())
    }

    @Test
    fun `execute returns list of expected pokemon with proper search query`() {
        val query = "char"
        val pokemon = arrayListOf(
                Pokemon("4", "Charmander", setOf(Fire)),
                Pokemon("5", "Charmeleon", setOf(Fire)),
                Pokemon("6", "Charizard", setOf(Fire))
        )
        val success = Success(pokemon)
        doReturn(success).whenever(pokemonSource).getByNameStartingWith(query)
        val response = searchPokemon.execute(SearchPokemonRequest(query)) as Success
        assertEquals(response.value.pokemon, pokemon)
    }

    @Test
    fun `execute calls appropriate search method with passed in query`() {
        val success = Success<List<Pokemon>>(emptyList())
        doReturn(success).whenever(pokemonSource).getByNameStartingWith(any())
        val request = SearchPokemonRequest("char")
        searchPokemon.execute(request) as Success
        verify(pokemonSource).getByNameStartingWith(request.query)
    }

}