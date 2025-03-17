package edu.icet.senuka.service;

import edu.icet.senuka.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(User user);
    User signup(User user);
    User updateUser(User user);
    Boolean deleteUser(Integer id);
    List<User> getAll();
}
