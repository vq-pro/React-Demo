package quebec.virtualite.backend.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.springframework.stereotype.Component;

import static com.jayway.restassured.RestAssured.given;

@Component
public class RestClient
{
    private static final char NON_BREAKING_SPACE = (char) 0x00A0;

    private Response response;

    public void get(String url)
    {
        response =
            given()
                .expect()
                .when()
                .get(url);
    }

    public void init(int serverPort)
    {
        RestAssured.port = serverPort;
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
}
