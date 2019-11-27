package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.MailBoxDTO;
import hu.me.fdsz.model.Message;
import hu.me.fdsz.model.User;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public interface MessageService {

    Map<User, LinkedList<Message>> getTopMessagesFromUsersGroupedBySender();

    List<MailBoxDTO> getMailboxContent();
}
