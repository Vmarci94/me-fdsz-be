package hu.me.fdsz.repository;

import hu.me.fdsz.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositroy extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);

    boolean existsByEmailAndUsername(String email, String username);

}
