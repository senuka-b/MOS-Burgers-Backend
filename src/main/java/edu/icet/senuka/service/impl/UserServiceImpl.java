package edu.icet.senuka.service.impl;

import edu.icet.senuka.dto.User;
import edu.icet.senuka.entity.UserEntity;
import edu.icet.senuka.repository.UserRepository;
import edu.icet.senuka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final UserRepository repository;

    @Override
    public Optional<User> login(User user) {
        Optional<UserEntity> optionalUser = repository.findFirstByEmailAndPassword(user.getEmail(), user.getPassword());
        return optionalUser.map( userEntity -> mapper.map(userEntity, User.class));
    }

    @Override
    public User signup(User user) {
        UserEntity entity = repository.save(mapper.map(user, UserEntity.class));
        return mapper.map(entity, User.class);
    }

    @Override
    public User updateUser(User user) {
        if (!repository.existsById(user.getId())) return null;

        UserEntity entity = repository.save(mapper.map(user, UserEntity.class));
        return mapper.map(entity, User.class);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        if (!repository.existsById(id)) return false;

        repository.deleteById(id);

        return true;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll()
                .stream()
                .map(userEntity -> mapper.map(userEntity, User.class))
                .toList();
    }
}
