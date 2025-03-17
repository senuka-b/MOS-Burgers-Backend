package edu.icet.senuka.repository;

import edu.icet.senuka.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findFirstByEmailAndPassword(String email, String password);
}
