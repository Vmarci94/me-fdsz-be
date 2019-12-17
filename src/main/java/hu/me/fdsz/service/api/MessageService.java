package hu.me.fdsz.service.api;

import hu.me.fdsz.model.dto.MailBoxDTO;
import hu.me.fdsz.model.entity.Message;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
public interface MessageService {

    List<MailBoxDTO> getMailboxContent();

    Optional<Message> add(Long userId, String messageContent) throws AuthenticationException;
}
