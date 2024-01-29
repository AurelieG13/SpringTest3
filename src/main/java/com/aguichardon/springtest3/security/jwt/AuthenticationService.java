package com.aguichardon.springtest3.security.jwt;

import com.aguichardon.springtest3.dto.AuthDTO;
import com.aguichardon.springtest3.model.UserAuth;
import com.aguichardon.springtest3.repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {


    private AuthRepository authRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private BCryptPasswordEncoder passwordEncoder;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPseudo(), request.getPassword()));

        UserAuth user = authRepository.findByPseudo(request.getPseudo());

        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setToken(jwtToken);

        return authResponse;
    }

    public void register(AuthDTO request) {
        // Vérifiez si l'utilisateur existe déjà
        if (authRepository.findByPseudo(request.getPseudo()) != null) {
            // Gérez l'erreur, par exemple, en lançant une exception
            throw new RuntimeException("L'utilisateur existe déjà");
        }

        // Encode le mot de passe avant de le stocker dans la base de données
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        encodedPassword = "{bcrypt}"+encodedPassword;

        // Créez un nouvel utilisateur avec le nom d'utilisateur et le mot de passe encodé
        UserAuth newUser = new UserAuth(request.getPseudo(), encodedPassword);


        //role
        newUser.setAuthority("ROLE_USER");
        //firstname
        //newUser.setFirstname("aurelie");
        // Enregistrez l'utilisateur dans la base de données
        authRepository.save(newUser);
    }
}