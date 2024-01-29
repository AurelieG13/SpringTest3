package com.aguichardon.springtest3.security;

import com.aguichardon.springtest3.security.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // vérifier le jeton JWT – il est passé dans l’en-tête
        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        // vérifier qu’il y a une donnée dans l’entête qui correspond à Authorization
        // l’entête contient Bearer <jeton> SINON erreur
        // Sinon laissé le comportement suivre son cours
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Il y a un JWT – il faut l’extraire
        jwt = authHeader.substring(7);// 7 correspond à Bearer

        // Vérification de l'utilisateur
        final String userEmail = jwtService.extractUserName(jwt);// Extraire du jeton JWT

        // Validation des données par rapport à la DB
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Check in DB
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // Validation du jeton JWT
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Gestion du contexte de sécurité de l’utilisateur
                // Création d'un nouveau jeton avec les informations et les rôles de l'utilisateur
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userEmail, null, userDetails.getAuthorities());
                //Transmettre les détails de la demande d’origine
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Mise à jour du contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
