package quebec.virtualite.backend.services.rest;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class GreetingResponse
{
    private String content;
}
