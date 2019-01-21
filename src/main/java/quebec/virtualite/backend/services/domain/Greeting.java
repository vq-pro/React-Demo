package quebec.virtualite.backend.services.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@Entity
@Table(name = "greetings")
public class Greeting
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Override
    public boolean equals(Object that)
    {
        return reflectionEquals(this, that);
    }

    @Override
    public int hashCode()
    {
        return reflectionHashCode(this);
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Greeting setName(String name)
    {
        this.name = name;
        return this;
    }
}
