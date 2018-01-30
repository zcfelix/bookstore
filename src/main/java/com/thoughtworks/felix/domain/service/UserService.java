package com.thoughtworks.felix.domain.service;

import com.thoughtworks.felix.domain.model.User;
import com.thoughtworks.felix.domain.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }
}
