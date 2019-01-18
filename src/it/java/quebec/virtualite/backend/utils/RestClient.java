package quebec.virtualite.backend.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.ResponseSpecification;
import org.springframework.stereotype.Component;

import static com.jayway.restassured.RestAssured.given;

@Component
public class RestClient
{
    private static final char NON_BREAKING_SPACE = (char) 0x00A0;

    private Response response;

    private String username;
    private String password;

    public void get(String url)
    {
        response = request().get(url);
    }

    public void init(int serverPort)
    {
        RestAssured.port = serverPort;
        clearUser();
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

    private ResponseSpecification request()
    {
        return given()
            .auth().basic(username, password)
            .expect()
            .when();
    }
}
