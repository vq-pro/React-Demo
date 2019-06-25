package quebec.virtualite.backend.services.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

@Entity
@Table(name = "greetings")
public class Greeting
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Greeting()
    {
    }

    public Greeting(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object that)
    {
        return reflectionEquals(this, that);
    }

    public long getId()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        return reflectionHashCode(this);
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
