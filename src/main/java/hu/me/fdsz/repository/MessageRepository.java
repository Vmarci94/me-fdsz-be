package hu.me.fdsz.repository;

import hu.me.fdsz.model.entity.Message;
import hu.me.fdsz.model.entity.User;
import hu.me.fdsz.model.enums.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllBySenderOrRecieverOrderByCreatedDate(User sender, User reciever);

    List<Message> findAllByReciever_RoleIsOrderByCreatedDate(Role reciever_role);

}
