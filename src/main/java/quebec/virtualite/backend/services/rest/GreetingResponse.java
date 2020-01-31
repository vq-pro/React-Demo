package quebec.virtualite.backend.services.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@Setter
public class GreetingResponse
{
    public String content;
}
