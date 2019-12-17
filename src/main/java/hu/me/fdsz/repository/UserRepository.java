package hu.me.fdsz.repository;

import hu.me.fdsz.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findByEmail(String email);

    boolean existsByEmailAndUsername(String email, String username);

    List<User> findAllByFullNameContaining(String fullName);

}
