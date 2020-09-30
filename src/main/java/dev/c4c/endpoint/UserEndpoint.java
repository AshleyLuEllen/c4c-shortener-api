package dev.c4c.endpoint;

import dev.c4c.data.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dev.c4c.data.user.UserService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "/admin/")
public class UserEndpoint {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id) {
        var user = userService.findUser(id);
        return user.orElse(null);
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/createuser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
