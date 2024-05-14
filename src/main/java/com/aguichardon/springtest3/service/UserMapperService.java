package com.aguichardon.springtest3.service;

import com.aguichardon.springtest3.dto.AuthDTO;
import com.aguichardon.springtest3.dto.UserAuthDTO;
import com.aguichardon.springtest3.dto.UserDTO;
import com.aguichardon.springtest3.model.User;
import com.aguichardon.springtest3.model.UserAuth;
import com.aguichardon.springtest3.repository.AuthRepository;
import com.aguichardon.springtest3.repository.UserRepository;
import com.aguichardon.springtest3.security.UserAlreadyExistsException;
import com.aguichardon.springtest3.security.jwt.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    private final AuthRepository authRepository;


    public UserMapperService(ModelMapper modelMapper, UserRepository userRepository, AuthenticationService authenticationService, AuthRepository authRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.authRepository = authRepository;
    }

    public void saveServiceDTO(UserDTO userDTO, AuthDTO authDTO) {

        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            // Utilisateur déjà existant, vous pouvez choisir de gérer cela en conséquence
            // Par exemple, lever une exception ou mettre à jour les informations de l'utilisateur existant
            throw new UserAlreadyExistsException("Utilisateur avec le même email déjà enregistré.");
        }
        //map dto
        User entityUser = modelMapper.map(userDTO, User.class);
        AuthDTO entityAuth = modelMapper.map(authDTO, AuthDTO.class);

        //save bdd
        User saveEntityUser = userRepository.save(entityUser);
        authenticationService.register(authDTO);
    }

    public UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email); // Remplacez findByUsername par la méthode appropriée pour récupérer l'utilisateur par son nom d'utilisateur
        return mapUserToDTO(user);
    }

}
