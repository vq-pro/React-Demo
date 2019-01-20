package quebec.virtualite.backend.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class RestClient
{
    private static final char NON_BREAKING_SPACE = (char) 0x00A0;

    private Response response;

    private String username;
    private String password;

    public void _init(int serverPort)
    {
        RestAssured.port = serverPort;
        clearUser();
    }

    public void get(String url)
    {
        response = request().get(url);
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

    private RequestSender request()
    {
        return given()
            .auth().basic(username, password)
            .expect()
            .when();
    }
}
