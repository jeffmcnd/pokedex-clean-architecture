package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test
import xyz.mcnallydawes.pokedex.boundary.GetTrainerInteractor
import xyz.mcnallydawes.pokedex.entity.Pokemon
import xyz.mcnallydawes.pokedex.entity.Trainer
import xyz.mcnallydawes.pokedex.request.GetTrainerRequest
import xyz.mcnallydawes.pokedex.source.TrainerSource

class GetTrainerTest {

    private val trainerSource: TrainerSource = mock()

    private val getTrainer: GetTrainerInteractor = GetTrainer(trainerSource)

    @Test
    fun `execute fails when trainer source fails`() {
        val id = "1"
        val failure = Failure<Pokemon?>(Throwable("Random throwable"))
        doReturn(failure).whenever(trainerSource).getById(id)
        val response = getTrainer.execute(GetTrainerRequest(id))
        assertEquals(response, failure)
    }

    @Test
    fun `execute fails when trainer not found`() {
        val id = "1"
        val success = Success(null)
        doReturn(success).whenever(trainerSource).getById(id)
        val response = getTrainer.execute(GetTrainerRequest(id))
        assertThat(response, instanceOf(Failure::class.java))
    }

    @Test
    fun `execute succeeds when trainer found`() {
        val id = "1"
        val trainer = Trainer(id, "Jeff", mutableListOf())
        val success = Success(trainer)
        doReturn(success).whenever(trainerSource).getById(id)
        val response = getTrainer.execute(GetTrainerRequest(id)) as Success
        assertEquals(response.value.trainer, trainer)
    }

}