package quebec.virtualite.backend.services.rest

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import quebec.virtualite.backend.services.domain.DomainService

private const val NAME = "name"

@RunWith(MockitoJUnitRunner::class)
class RestServerTest
{
    @InjectMocks
    var server = RestServer()

    @Mock
    lateinit var mockedDomainService: DomainService

    @Test
    fun greet()
    {
        // When
        val response = server.greet(NAME)

        // Then
        verify(mockedDomainService).recordGreeting(NAME)

        assertThat(response.content, equalTo("Hello " + NAME + "!"))
    }
}