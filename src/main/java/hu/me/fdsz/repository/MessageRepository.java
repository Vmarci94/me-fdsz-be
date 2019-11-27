package hu.me.fdsz.repository;

import hu.me.fdsz.model.Message;
import hu.me.fdsz.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(value = "select m from Message m where m.sender = :user or m.reciever = :user order by m.createdDate")
    List<Message> findAllMessagesToUser(User user);

}
