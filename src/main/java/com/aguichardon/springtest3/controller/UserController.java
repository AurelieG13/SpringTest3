package com.aguichardon.springtest3.controller;

import com.aguichardon.springtest3.dto.UserAuthDTO;
import com.aguichardon.springtest3.dto.UserDTO;
import com.aguichardon.springtest3.model.Sport;
import com.aguichardon.springtest3.model.User;
import com.aguichardon.springtest3.repository.UserRepository;
import com.aguichardon.springtest3.service.UserMapperService;
import com.aguichardon.springtest3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapperService userMapperService;
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserMapperService userMapperService, UserRepository userRepository, UserService userService) {
        this.userMapperService = userMapperService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
            return userService.getAllUser();
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

    @GetMapping("/currentUser")
    public UserDTO getCurrentUser() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userMapperService.getUserByEmail(email);
    }

    @PutMapping("/currentUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userService.findById(id);
        if (existingUser != null) {
            updatedUser.setId(id); // Assurez-vous que l'ID de l'utilisateur reste le même
            User savedUser = userService.save(updatedUser); // Enregistrez les modifications dans la base de données
            return ResponseEntity.ok(savedUser);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utilisateur non trouvé
        }
    }


}

