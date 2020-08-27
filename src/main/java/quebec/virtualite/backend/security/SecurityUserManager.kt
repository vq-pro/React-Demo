package quebec.virtualite.backend.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class SecurityUserManager
{
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private var passwordEncoder: PasswordEncoder? = null

    @PostConstruct
    fun _init()
    {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    fun defineUser(username: String, password: String?)
    {
        if (doesUserExist(username))
            return

        jdbcTemplate.update("INSERT INTO users (username, password, enabled)"
            + " VALUES (?, ?, TRUE)", username, passwordEncoder!!.encode(password))

        jdbcTemplate.update("INSERT INTO authorities (username, authority)"
            + " VALUES (?, 'ROLE_USER')", username)
    }

    private fun doesUserExist(username: String): Boolean
    {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE username = ?",
            arrayOf<Any>(username), Int::class.java) == 1
    }
}