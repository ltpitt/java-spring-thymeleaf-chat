package it.davidenastri.chat.config;

import it.davidenastri.chat.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(this.authenticationService)
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)        // set invalidation state when logout
                        .deleteCookies("JSESSIONID")
                )
                .authorizeHttpRequests(authorize -> authorize
                        // allow access to static resources
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        // allow access to signup and logout paths
                        .requestMatchers("/signup", "/logout").permitAll()
                        // require authentication for any other request
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/chat", true)
                );
        return http.build();
    }
}
