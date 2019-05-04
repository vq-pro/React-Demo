package quebec.virtualite.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static quebec.virtualite.backend.security.SecurityUsers.TEST_PASSWORD;
import static quebec.virtualite.backend.security.SecurityUsers.TEST_USER;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfiguration
{
    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http)
    {
        return http
            .authorizeExchange()
            .anyExchange().authenticated()
            .and().httpBasic()
            .and().build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService()
    {
        return new MapReactiveUserDetailsService(
            User
                .withUsername(TEST_USER)
                .password(passwordEncoder().encode(TEST_PASSWORD))
                .roles("USER")
                .build());
    }

    private PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
