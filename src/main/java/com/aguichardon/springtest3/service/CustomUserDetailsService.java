package com.aguichardon.springtest3.service;


import com.aguichardon.springtest3.model.User;
import com.aguichardon.springtest3.model.UserAuth;
import com.aguichardon.springtest3.repository.AuthRepository;
import com.aguichardon.springtest3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {

        UserAuth userAuth = authRepository.findByPseudo(pseudo);

        if (userAuth == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur: " + pseudo);
        }
        // Retourne un objet UserDetails qui représente l'utilisateur dans votre application
        return org.springframework.security.core.userdetails.User.builder()
                .username(userAuth.getUsername())
                .password(userAuth.getPassword())
                .authorities(userAuth.getAuthorities())
                .build();
    }
}
