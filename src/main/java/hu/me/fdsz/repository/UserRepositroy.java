package hu.me.fdsz.repository;

import hu.me.fdsz.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositroy extends CrudRepository<User, Long> {

}
