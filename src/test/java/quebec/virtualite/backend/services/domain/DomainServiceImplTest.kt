package quebec.virtualite.backend.services.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNot.not
import org.hamcrest.core.IsNull.nullValue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import quebec.virtualite.backend.services.domain.database.GreetingRepository
import quebec.virtualite.backend.services.domain.entities.GreetingEntity

private const val NAME = "name"

@RunWith(MockitoJUnitRunner::class)
class DomainServiceImplTest
{
    @InjectMocks
    var domainService = DomainServiceImpl()

    @Mock
    lateinit var mockedGreetingRepository: GreetingRepository

    @Test
    fun deleteGreetings()
    {
        // When
        domainService.deleteGreetings()

        // Then
        verify(mockedGreetingRepository).deleteAll()
    }

    @Test
    fun getGreetings()
    {
        // When
        val greetings = domainService.getGreetings()

        // Then
        verify(mockedGreetingRepository).findAll()

        assertThat(greetings, not(nullValue()))
    }

    @Test
    fun recordGreeting()
    {
        // When
        domainService.recordGreeting(NAME)

        // Then
        verify(mockedGreetingRepository).findByName(NAME)
        verify(mockedGreetingRepository).save(GreetingEntity(NAME))
    }
}