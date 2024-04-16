package com.aguichardon.springtest3.service;

import com.aguichardon.springtest3.model.Sport;
import com.aguichardon.springtest3.model.User;
import com.aguichardon.springtest3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Méthode pour sauvegarder ou mettre à jour un utilisateur
    public User save(User user) {
        return userRepository.save(user);
    }

}
