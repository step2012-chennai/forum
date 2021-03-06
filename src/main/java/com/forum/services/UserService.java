package com.forum.services;

import com.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String userName, String location, String email, String name, String dob, String gender, String password) {
        userRepository.register(userName, name, password, dob, location, gender, email);
    }

    public boolean isUserNameExists(String username) {
        return userRepository.isUserNameExists(username);
    }
}