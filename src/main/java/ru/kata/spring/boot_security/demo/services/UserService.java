package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    void updateUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    Optional<User> findByEmail(String email);

    List<User> getAllUsers();
}
