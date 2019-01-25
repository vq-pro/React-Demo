package quebec.virtualite.backend.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@Component
public class RestClient
{
    private static final char NON_BREAKING_SPACE = (char) 0x00A0;
    private static final String XSRF_TOKEN = "XSRF-TOKEN";
    private static final String X_XSRF_TOKEN = "X-XSRF-TOKEN";

    private Response response;

    private String username;
    private String password;

    public void _init(int serverPort)
    {
        RestAssured.port = serverPort;
        clearUser();
    }

    public void get(String url, RestParam param)
    {
        url = setParam(url, param);

        response = requestForRead().get(url);
    }

    public void login(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void logout()
    {
        clearUser();
    }

    public void post(String url, RestParam param)
    {
        url = setParam(url, param);

        response = requestForWrite()
            .post(url);
    }

    public Response response()
    {
        return response;
    }

    public String trim(String json)
    {
        return json
            .replace(" ", "")
            .replace("\r", "")
            .replace("\n", "")
            .replace(NON_BREAKING_SPACE, ' ');
    }

    private void clearUser()
    {
        username = "";
        password = "";
    }

    private String getJSessionID()
    {
        return given()
            .auth().basic(username, password)
            .get("/user")
            .getSessionId();
    }

    private String getToken(String jSessionID)
    {
        return given()
            .sessionId(jSessionID)
            .contentType(JSON)
            .get("/user")
            .cookie(XSRF_TOKEN);
    }

    private RequestSender requestForRead()
    {
        // FIXME1 Cleaner check for logged-in or not
        return given()
            .auth().basic(username, password)
            .expect()
            .when();
    }

    private RequestSender requestForWrite()
    {
        // FIXME1 Cleaner check for logged-in or not
        String jSessionID = getJSessionID();
        String token = getToken(jSessionID);

        return given()
            .sessionId(jSessionID)
            .header(X_XSRF_TOKEN, token)
            .contentType(JSON)
            .expect()
            .when();
    }

    private String setParam(String url, RestParam param)
    {
        String paramName = "{" + param.key + "}";
        assertThat("Error in URL", url, containsString(paramName));

        return url.replace(paramName, String.valueOf(param.value));
    }
}
