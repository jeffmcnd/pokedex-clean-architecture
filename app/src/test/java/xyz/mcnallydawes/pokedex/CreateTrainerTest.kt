package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.*
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.*
import org.junit.Test
import xyz.mcnallydawes.pokedex.entity.Trainer
import xyz.mcnallydawes.pokedex.request.CreateTrainerRequest
import xyz.mcnallydawes.pokedex.source.TrainerSource

class CreateTrainerTest {

    private val trainerSource: TrainerSource = mock()
    private val createTrainer: CreateTrainer = CreateTrainer(trainerSource)

    @Test
    fun `execute succeeds when name isn't taken`() {
        val name = "Jeff"
        doReturn(Success(null)).whenever(trainerSource).getByName(name)
        doReturn(Success("1")).whenever(trainerSource).save(any())
        val response = createTrainer.execute(CreateTrainerRequest(name))
        assertThat(response, instanceOf(Success::class.java))
    }

    @Test
    fun `execute fails when name is taken`() {
        val name = "Jeff"
        doReturn(Success(Trainer("1", name, mutableListOf()))).whenever(trainerSource).getByName(name)
        val response = createTrainer.execute(CreateTrainerRequest(name))
        assertThat(response, instanceOf(Failure::class.java))
    }

    @Test
    fun `execute saves new trainer`() {
        val name = "Jeff"
        doReturn(Success(null)).whenever(trainerSource).getByName(name)
        createTrainer.execute(CreateTrainerRequest(name))
        verify(trainerSource).save(Trainer(name = name))
    }

    @Test
    fun `execute returns new trainer with non-null id`() {
        val name = "Jeff"
        doReturn(Success(null)).whenever(trainerSource).getByName(name)
        doReturn(Success("1")).whenever(trainerSource).save(any())
        val response = createTrainer.execute(CreateTrainerRequest(name)) as Success
        assertNotNull(response.value.trainer.id)
    }

    @Test
    fun `execute fails if trainer source getByName fails`() {
        val name = "Jeff"
        val failure = Failure<Trainer?>(Throwable("Failure"))
        doReturn(failure).whenever(trainerSource).getByName(name)
        val response = createTrainer.execute(CreateTrainerRequest(name))
        assertEquals(response, failure)
    }

    @Test
    fun `execute fails if trainer source save fails`() {
        val name = "Jeff"
        val failure = Failure<String>(Throwable("Failure"))
        doReturn(Success(null)).whenever(trainerSource).getByName(name)
        doReturn(failure).whenever(trainerSource).save(any())
        val response = createTrainer.execute(CreateTrainerRequest(name))
        assertEquals(response, failure)
    }

}
