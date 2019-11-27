package hu.me.fdsz.service.api;

import hu.me.fdsz.model.Message;
import hu.me.fdsz.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<Message> getAllMessageToUser(User currentUser);
}
