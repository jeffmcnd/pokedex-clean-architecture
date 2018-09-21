package xyz.mcnallydawes.pokedex

import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.*
import org.junit.Test
import xyz.mcnallydawes.pokedex.domain.entity.Trainer
import xyz.mcnallydawes.pokedex.domain.request.SearchTrainersRequest
import xyz.mcnallydawes.pokedex.domain.source.TrainerSource

class SearchTrainersTest {
    private val trainerSource: TrainerSource = mock()
    private val searchTrainers: SearchTrainers = SearchTrainers(trainerSource)

    @Test
    fun `execute fails when trainer source fails`() {
        val query = "char"
        val failure = Failure<List<Trainer>>(Throwable("Random throwable"))
        doReturn(failure).whenever(trainerSource).getByNameStartingWith(query)
        val response = searchTrainers.execute(SearchTrainersRequest(query))
        assertEquals(response, failure)
    }

    @Test
    fun `execute returns empty list when no trainers are found`() {
        val query = "non-existing-trainers-search"
        val success = Success<List<Trainer>>(emptyList())
        doReturn(success).whenever(trainerSource).getByNameStartingWith(query)
        val response = searchTrainers.execute(SearchTrainersRequest(query)) as Success
        assertTrue(response.value.trainers.isEmpty())
    }

    @Test
    fun `execute returns list of expected trainers with proper search query`() {
        val query = "Al"
        val trainers = arrayListOf(
                Trainer("1", "Alice", mutableListOf()),
                Trainer("2", "Bob", mutableListOf()),
                Trainer("3", "Alex", mutableListOf()),
                Trainer("4", "Charlie", mutableListOf())
        )
        val success = Success(trainers)
        doReturn(success).whenever(trainerSource).getByNameStartingWith(query)
        val response = searchTrainers.execute(SearchTrainersRequest(query)) as Success
        assertEquals(response.value.trainers, trainers)
    }

    @Test
    fun `execute calls appropriate search method with passed in query`() {
        val query = "Al"
        val success = Success<List<Trainer>>(emptyList())
        doReturn(success).whenever(trainerSource).getByNameStartingWith(query)
        val request = SearchTrainersRequest(query)
        searchTrainers.execute(request) as Success
        verify(trainerSource).getByNameStartingWith(request.query)
    }

}