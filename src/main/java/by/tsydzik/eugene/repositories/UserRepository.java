package by.tsydzik.eugene.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import by.tsydzik.eugene.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findByNameOrEmail(String name, String email);

    List<User> findAllFriends(@Param("userId") Long userId);

    List<User> findByIdNot(Long id);

    List<User> findByNameStartingWith(String text);
}
