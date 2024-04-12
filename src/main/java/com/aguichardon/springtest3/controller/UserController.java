package com.aguichardon.springtest3.controller;

import com.aguichardon.springtest3.dto.UserAuthDTO;
import com.aguichardon.springtest3.model.Sport;
import com.aguichardon.springtest3.model.User;
import com.aguichardon.springtest3.model.UserAuth;
import com.aguichardon.springtest3.service.UserMapperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapperService userMapperService;

    public UserController(UserMapperService userMapperService) {
        this.userMapperService = userMapperService;
    }



    @PostMapping("/saveUser")
    public ResponseEntity<HttpStatus> saveUserDTO(@RequestBody UserAuthDTO userAuthDTO) {
        try {
            userMapperService.saveServiceDTO(userAuthDTO.getUserDTO(), userAuthDTO.getAuthDTO());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
