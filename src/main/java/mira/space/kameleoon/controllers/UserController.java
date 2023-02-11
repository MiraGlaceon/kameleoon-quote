package mira.space.kameleoon.controllers;

import jakarta.servlet.http.HttpSession;
import mira.space.kameleoon.models.User;
import mira.space.kameleoon.models.repositories.UserRepository;
import mira.space.kameleoon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, HttpSession session) {
        if (!userService.isUserValid(user)) {
            return ResponseEntity.badRequest().build();
        }
        user.setSessionId(session.getId());
        return ResponseEntity.ok(userRepository.save(user));
    }

}
