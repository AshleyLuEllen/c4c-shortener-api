package dev.c4c.data.user;

import java.util.List;
import java.util.Optional;

import dev.c4c.data.Role;
import dev.c4c.data.user.User;
import dev.c4c.data.user.UserRepository;
import dev.c4c.error.ResourceConflictException;
import dev.c4c.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        // Check email
        userRepository.findByEmailAddress(user.getEmailAddress()).ifPresent(u -> {
            throw new ResourceConflictException("Email address is taken.");
        });

        // Create user
        user.setPasswordHash(WebSecurityConfig.PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(Role.OFFICER);
        return userRepository.save(user);
    }
}
