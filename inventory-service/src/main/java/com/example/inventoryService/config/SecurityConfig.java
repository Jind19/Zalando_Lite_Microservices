package com.example.inventoryService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures security for the Inventory Service using Spring Security.
 *
 * This class sets up the application as an OAuth2 Resource Server that requires
 * all incoming HTTP requests to be authenticated with a valid JWT token.
 *
 * It enforces that every request contains an Authorization header with a Bearer token,
 * which is validated against the configured JWT issuer (e.g., Google).
 *
 * If the token is missing or invalid, the request will be rejected with HTTP 401 Unauthorized.
 *
 * This configuration ensures secure access to all REST endpoints in the service.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     *
     * Requires all requests to be authenticated with a valid JWT token
     * in the Authorization header using OAuth2 Resource Server support.
     *
     * The JWT token is validated based on the configured issuer URI.
     *
     * @param httpSecurity the HttpSecurity object to configure security settings
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while building the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //✅ Every HTTP request to any endpoint must be authenticated.
        //❌ If there's no JWT token in the request, the server will respond with 401 Unauthorized.
        httpSecurity.authorizeHttpRequests(
                auth ->
                        auth.anyRequest().authenticated()

        )
                //🔐 This configures your app as an OAuth2 Resource Server.
                //This means the app expects an Authorization header like this:
                //Authorization: Bearer eyJhbGciOi...
                //It will validate the JWT token using the issuer you configure in application.properties
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwtConfigurer -> {

                                }

                        )
                );

        //This returns the fully built SecurityFilterChain,
        //which Spring registers internally to enforce these security rules.
        return httpSecurity.build();
    }
}
