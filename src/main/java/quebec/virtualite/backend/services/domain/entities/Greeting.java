package quebec.virtualite.backend.services.domain.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "greetings")
@Accessors(fluent = true)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
