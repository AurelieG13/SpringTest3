package com.aguichardon.springtest3.service;

import com.aguichardon.springtest3.dto.AuthDTO;
import com.aguichardon.springtest3.dto.UserDTO;
import com.aguichardon.springtest3.model.User;
import com.aguichardon.springtest3.model.UserAuth;
import com.aguichardon.springtest3.repository.AuthRepository;
import com.aguichardon.springtest3.repository.UserRepository;
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
        //map dto
        User entityUser = modelMapper.map(userDTO, User.class);
        AuthDTO entityAuth = modelMapper.map(authDTO, AuthDTO.class);

        //save bdd
        User saveEntityUser = userRepository.save(entityUser);
        authenticationService.register(authDTO);
    }

}
