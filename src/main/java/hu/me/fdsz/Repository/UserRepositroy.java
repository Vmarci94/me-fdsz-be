package hu.me.fdsz.Repository;

import hu.me.fdsz.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositroy extends CrudRepository<User, Long> {

}
