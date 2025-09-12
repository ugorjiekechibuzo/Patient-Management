package com.pm.authservice.service;

import com.pm.authservice.model.User;
import com.pm.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    //Using dependency injection to tell spring to inject UserRepo int our UserService class and assign it to the
    //class variable created with final
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {

        //Any Business Logic to find the user
        return userRepository.findByEmail(email);
    }


}
