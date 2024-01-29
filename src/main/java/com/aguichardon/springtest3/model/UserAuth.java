package com.aguichardon.springtest3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usersConnect")
public class UserAuth implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 250)
    private String pseudo;

    @Column(length = 68, nullable = false)
    private String password;

    @Column(length = 15, nullable = false)
    private String authority;

    public UserAuth(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    //Correspond aux rôles de l'utilisateur
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(authority));
    }

    // Correspond à l'élément unique d'authentification
    @Override
    public String getUsername() {
        return pseudo;
    }

    // Etat du compte utilisateur – compte non expiré ?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Etat du compte utilisateur – non verrouillé ?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indique si les informations d’identification sont non expirées ?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Etat du compte utilisateur – actif ?
    @Override
    public boolean isEnabled() {
        return true;
    }
}