package com.aguichardon.springtest3.security;

import jakarta.servlet.Filter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private Filter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    /**
     *
     * Récupération des utilisateurs de l'application via la base de données
     */
   /* @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select pseudo, password, 1 from users where pseudo=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select pseudo, authority from users where pseudo=?");

        return jdbcUserDetailsManager; }*/

    /**
     * Restriction des URLs selon la connexion utilisateur et leurs rôles
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    //Permettre l'accès à l'URL racine à tout le monde
                    .requestMatchers("/api/sports").permitAll()
                    .requestMatchers("/api/sports/all").permitAll()
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/ticketing/**").permitAll()
                    .requestMatchers("/api/users/saveUser").permitAll()

                    //Permettre aux rôles EMPLOYE et ADMIN de manipuler les URLs en GET

                    //Restreindre la manipulation des méthodes POST, PUT, PATCH et DELETE au rôle ADMIN
                    .requestMatchers(HttpMethod.POST, "/api/sports/create").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/sports/save").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/sports").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/api/sports").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/sports").hasRole("ADMIN")

                    //Toutes autres url et méthodes HTTP ne sont pas permises si on met .denyall() au lieu de permitAll()
                    .anyRequest().permitAll();
        });

        //Use Http Basic Authentication
        //http.httpBasic(Customizer.withDefaults());

        //Désactivé Cross Site Request Forgery
        // Non préconisé pour les API REST en stateless. Sauf pour POST, PUT, PATCH et DELETE
        http.csrf(csrf -> {
            csrf.disable();
        });

        //Connexion de l'utilisateur
        http.authenticationProvider(authenticationProvider);

        //Activer le filtre JWT et l'authentication de l'utilisateur
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Session Stateless
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        //logout
        http
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .addLogoutHandler(new SecurityContextLogoutHandler())
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
