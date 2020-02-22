package quebec.virtualite.backend.services.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class GreetingResponse
{
    private String content;
}
