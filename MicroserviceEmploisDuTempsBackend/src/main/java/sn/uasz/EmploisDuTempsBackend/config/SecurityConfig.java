package sn.uasz.EmploisDuTempsBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactiver la protection CSRF (si besoin)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Autoriser toutes les requêtes sans authentification
                )
                .formLogin(form -> form.disable()) // Désactiver le formulaire de connexion
                .httpBasic(basic -> basic.disable()); // Désactiver l'authentification HTTP Basic

        return http.build();
    }
}