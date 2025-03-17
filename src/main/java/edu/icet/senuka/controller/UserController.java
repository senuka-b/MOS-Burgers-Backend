package edu.icet.senuka.controller;

import edu.icet.senuka.dto.User;
import edu.icet.senuka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return service.login(user)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> all = service.getAll();

        if (!all.isEmpty()) {
            return ResponseEntity.ok(all);
        }

        return ResponseEntity.noContent().build();

    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(service.signup(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(user));
    }
}
