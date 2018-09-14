package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import xyz.mcnallydawes.pokedex.entity.Fire
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.entity.Trainer
import xyz.mcnallydawes.pokedex.request.AddPokemonToTrainerRequest
import xyz.mcnallydawes.pokedex.source.PokemonSource
import xyz.mcnallydawes.pokedex.source.TrainerSource

class AddPokemonToTrainerTest {
    private val trainerSource: TrainerSource = mock()
    private val pokemonSource: PokemonSource = mock()
    private val addPokemonToTrainer: AddPokemonToTrainer = AddPokemonToTrainer(trainerSource, pokemonSource)

    @Test
    fun `execute succeeds when trainer and pokemon exist`() {
        val id = "1"
        val trainer = Trainer(id, "Jeff", mutableListOf())
        val pokemon = Pokemon(id, "Charmander", setOf(Fire))
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        doReturn(Success(pokemon)).whenever(pokemonSource).getById(id)
        val response = addPokemonToTrainer.execute(AddPokemonToTrainerRequest(id, id))
        assertThat(response, instanceOf(Failure::class.java))
    }

    @Test
    fun `execute saves trainer`() {
        val id = "1"
        val trainer = Trainer(id, "Jeff", mutableListOf())
        val pokemon = Pokemon(id, "Charmander", setOf(Fire))
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        doReturn(Success(pokemon)).whenever(pokemonSource).getById(id)
        addPokemonToTrainer.execute(AddPokemonToTrainerRequest(id, id)) as Success
        trainer.starters.add(pokemon)
        verify(trainerSource).save(trainer)
    }

    @Test
    fun `execute returns altered trainer`() {
        val id = "1"
        val trainer = Trainer(id, "Jeff", mutableListOf())
        val pokemon = Pokemon(id, "Charmander", setOf(Fire))
        val pokemonList = listOf(pokemon)
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        doReturn(Success(pokemon)).whenever(pokemonSource).getById(id)
        val response = addPokemonToTrainer.execute(AddPokemonToTrainerRequest(id, id)) as Success
        assertEquals(pokemonList, response.value.trainer.starters)
    }

    @Test
    fun `execute fails when pokemon doesn't exist`() {
        val id = "1"
        val trainer = Trainer(id, "Jeff", mutableListOf())
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        doReturn(Success(null)).whenever(pokemonSource).getById(id)
        val response = addPokemonToTrainer.execute(AddPokemonToTrainerRequest(id, id))
        assertThat(response, instanceOf(Failure::class.java))
    }

    @Test
    fun `execute fails when trainer doesn't exist`() {
        val id = "1"
        val pokemon = Pokemon(id, "Charmander", setOf(Fire))
        doReturn(Success(null)).whenever(trainerSource).getById(id)
        doReturn(Success(pokemon)).whenever(pokemonSource).getById(id)
        val response = addPokemonToTrainer.execute(AddPokemonToTrainerRequest(id, id))
        assertThat(response, instanceOf(Failure::class.java))
    }

    @Test
    fun `execute fails when trainer source fails`() {
        val id = "1"
        val pokemon = Pokemon(id, "Charmander", setOf(Fire))
        doReturn(Failure<Trainer?>(Throwable("Failed"))).whenever(trainerSource).getById(id)
        doReturn(Success(pokemon)).whenever(pokemonSource).getById(id)
        val response = addPokemonToTrainer.execute(AddPokemonToTrainerRequest(id, id))
        assertThat(response, instanceOf(Failure::class.java))
    }

    @Test
    fun `execute fails when pokemon source fails`() {
        val id = "1"
        val trainer = Trainer(id, "Jeff", mutableListOf())
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        doReturn(Failure<Pokemon?>(Throwable("Failed"))).whenever(pokemonSource).getById(id)
        val response = addPokemonToTrainer.execute(AddPokemonToTrainerRequest(id, id))
        assertThat(response, instanceOf(Failure::class.java))
    }

}