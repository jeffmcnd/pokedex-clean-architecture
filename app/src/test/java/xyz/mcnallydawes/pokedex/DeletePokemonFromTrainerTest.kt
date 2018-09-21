package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import xyz.mcnallydawes.pokedex.domain.entity.Fire
import xyz.mcnallydawes.pokedex.domain.entity.Pokemon
import xyz.mcnallydawes.pokedex.domain.entity.Trainer
import xyz.mcnallydawes.pokedex.domain.entity.Water
import xyz.mcnallydawes.pokedex.domain.request.DeletePokemonFromTrainerRequest
import xyz.mcnallydawes.pokedex.domain.response.DeletePokemonFromTrainerResponse
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class DeletePokemonFromTrainerTest {

    private val trainerSource: TrainerSource = mock()
    private val deletePokemonFromTrainer: DeletePokemonFromTrainer = DeletePokemonFromTrainer(trainerSource)

    @Test
    fun `execute removes pokemon from roster`() {
        val id = "1"
        val charmander = Pokemon(id, "Charmander", setOf(Fire))
        val trainer = Trainer(id, "Jeff", mutableListOf(charmander))
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        val response = deletePokemonFromTrainer.execute(DeletePokemonFromTrainerRequest(id, id)) as Success
        assertTrue(response.value.trainer.starters.isEmpty())
    }

    @Test
    fun `execute removes all instances of pokemon with matching id`() {
        val id = "1"
        val charmander = Pokemon("1", "Charmander", setOf(Fire))
        val squirtle = Pokemon("7", "Squirtle", setOf(Water))
        val trainer = Trainer(id, "Jeff", mutableListOf(charmander, squirtle, charmander, charmander))
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        val response = deletePokemonFromTrainer.execute(DeletePokemonFromTrainerRequest(id, id)) as Success
        assertTrue(response.value.trainer.starters.size == 1)
    }

    @Test
    fun `execute succeeds if pokemon is not in roster`() {
        val id = "1"
        val pokemon = Pokemon("4", "Charmander", setOf(Fire))
        val trainer = Trainer(id, "Jeff", mutableListOf(pokemon))
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        val response = deletePokemonFromTrainer.execute(DeletePokemonFromTrainerRequest(id, id))
        assertThat(response, instanceOf(Success::class.java))
    }

    @Test
    fun `execute saves expected trainer`() {
        val id = "1"
        val pokemon = Pokemon(id, "Charmander", setOf(Fire))
        val trainer = Trainer(id, "Jeff", mutableListOf(pokemon))
        doReturn(Success(trainer)).whenever(trainerSource).getById(id)
        deletePokemonFromTrainer.execute(DeletePokemonFromTrainerRequest(id, id)) as Success
        verify(trainerSource).save(trainer.copy(starters = mutableListOf()))
    }

    @Test
    fun `execute fails when trainer source fails`() {
        val id = "1"
        doReturn(Failure<DeletePokemonFromTrainerResponse>(Throwable("Failure"))).whenever(trainerSource).getById(id)
        val response = deletePokemonFromTrainer.execute(DeletePokemonFromTrainerRequest(id, id))
        assertThat(response, instanceOf(Failure::class.java))
    }

    @Test
    fun `execute fails when trainer doesn't exist`() {
        val id = "1"
        doReturn(Success(null)).whenever(trainerSource).getById(id)
        val response = deletePokemonFromTrainer.execute(DeletePokemonFromTrainerRequest(id, id))
        assertThat(response, instanceOf(Failure::class.java))
    }
}