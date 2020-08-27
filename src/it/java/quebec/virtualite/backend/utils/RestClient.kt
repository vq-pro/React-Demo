package quebec.virtualite.backend.utils

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertThat
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class RestClient
{
    private var response: Response? = null
    private var username: String? = null
    private var password: String? = null

    fun _init(serverPort: Int)
    {
        RestAssured.port = serverPort
        clearUser()
    }

    operator fun get(url: String, param: RestParam)
    {
        response = requestForReads()[setParam(url, param)]
    }

    fun login(username: String?, password: String?)
    {
        this.username = username
        this.password = password
    }

    fun logout()
    {
        clearUser()
    }

    fun post(url: String, param: RestParam)
    {
        response = requestForWrites()
            .contentType(ContentType.JSON)
            .post(setParam(url, param))
    }

    fun put(url: String, param: RestParam)
    {
        response = requestForWrites()
            .contentType(ContentType.JSON)
            .put(setParam(url, param))
    }

    fun response(): Response
    {
        return response!!
    }

    fun trim(json: String): String
    {
        return json
            .replace(" ", "")
            .replace("\r", "")
            .replace("\n", "")
            .replace(NON_BREAKING_SPACE, ' ')
    }

    private fun clearUser()
    {
        username = ""
        password = ""
    }

    private val jSessionID: String
        get() = given()
            .auth().basic(username, password)["/user"]
            .sessionId

    private fun getToken(jSessionID: String): String
    {
        return given()
            .sessionId(jSessionID)
            .contentType(ContentType.JSON)["/user"]
            .cookie(XSRF_TOKEN)
    }

    private fun notIsLoggedIn(): Boolean
    {
        return StringUtils.isEmpty(username) || StringUtils.isEmpty(password)
    }

    private fun requestForReads(): RequestSpecification
    {
        return if (notIsLoggedIn())
            given()
        else given()
            .auth()
            .basic(username, password)
    }

    private fun requestForWrites(): RequestSpecification
    {
        if (notIsLoggedIn())
            return given()

        val jSessionID = jSessionID
        val token = getToken(jSessionID)

        return given()
            .sessionId(jSessionID)
            .header(X_XSRF_TOKEN, token)
    }

    private fun setParam(url: String, param: RestParam): String
    {
        val paramName = "{" + param.key + "}"

        assertThat("Error in URL", url, containsString(paramName))
        return url.replace(paramName, param.value.toString())
    }

    companion object
    {
        private const val NON_BREAKING_SPACE = 0x00A0.toChar()
        private const val XSRF_TOKEN = "XSRF-TOKEN"
        private const val X_XSRF_TOKEN = "X-XSRF-TOKEN"
    }
}