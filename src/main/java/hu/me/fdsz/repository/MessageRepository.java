package hu.me.fdsz.repository;

import hu.me.fdsz.model.entities.Message;
import hu.me.fdsz.model.entities.User;
import hu.me.fdsz.model.enums.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(value = "select m from Message m where m.sender = :user or m.reciever = :user order by m.createdDate")
    List<Message> findAllMessagesToUser(User user);

    List<Message> findAllBySenderOrRecieverOrderByCreatedDate(User sender, User reciever);

    List<Message> findAllByReciever_RoleIsOrderByCreatedDate(Role reciever_role);
}
