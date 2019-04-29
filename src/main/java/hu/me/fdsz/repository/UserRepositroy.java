package hu.me.fdsz.repository;

import hu.me.fdsz.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositroy extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    boolean existsByEmailAndUserName(String email, String username);

}
