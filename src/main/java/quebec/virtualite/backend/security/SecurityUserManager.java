package quebec.virtualite.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SecurityUserManager
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void _init()
    {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void defineUser(String username, String password)
    {
        if (doesUserExist(username))
            return;

        jdbcTemplate.update(
            "INSERT INTO users (username, password, enabled)"
            + " VALUES (?, ?, TRUE)",
            username,
            passwordEncoder.encode(password));

        jdbcTemplate.update(
            "INSERT INTO authorities (username, authority)"
            + " VALUES (?, 'ROLE_USER')",
            username);
    }

    private boolean doesUserExist(String username)
    {
        return jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM users WHERE username = ?",
            new Object[]{username},
            Integer.class) == 1;
    }
}
