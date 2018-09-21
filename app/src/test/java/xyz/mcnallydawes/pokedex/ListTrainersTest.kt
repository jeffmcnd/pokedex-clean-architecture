package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test
import xyz.mcnallydawes.pokedex.domain.entity.Trainer
import xyz.mcnallydawes.pokedex.domain.request.ListTrainersRequest
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class ListTrainersTest {

    private val trainerSource: TrainerSource = mock()

    private val listTrainers: ListTrainers = ListTrainers(trainerSource)

    @Test
    fun `execute fails when trainer source fails`() {
        val failure = Failure<List<Trainer>>(Throwable("Random throwable"))
        doReturn(failure).whenever(trainerSource).getAll()
        val response = listTrainers.execute(ListTrainersRequest())
        assertEquals(response, failure)
    }

    @Test
    fun `execute returns empty list when trainer source is empty`() {
        val success = Success(ArrayList<Trainer>())
        doReturn(success).whenever(trainerSource).getAll()
        val response = listTrainers.execute(ListTrainersRequest()) as Success
        assertEquals(response.value.trainers, success.value)
    }

    @Test
    fun `execute returns correct list`() {
        val success = Success(listOf(Trainer("1", "Jeff", mutableListOf())))
        doReturn(success).whenever(trainerSource).getAll()
        val response = listTrainers.execute(ListTrainersRequest()) as Success
        assertEquals(response.value.trainers, success.value)
    }

}