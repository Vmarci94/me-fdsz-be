package hu.me.fdsz.service.api;

import hu.me.fdsz.model.Message;
import hu.me.fdsz.model.User;
import hu.me.fdsz.model.dto.MailBoxDTO;
import hu.me.fdsz.model.dto.MessageDTO;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface MessageService {

    Map<User, LinkedList<Message>> getTopMessagesFromUsersGroupedBySender();

    List<MailBoxDTO> getMailboxContent();

    boolean deleteAllMessageFromCurrentUser() throws AuthenticationException;

    Optional<Message> add(MessageDTO message) throws AuthenticationException;
}
