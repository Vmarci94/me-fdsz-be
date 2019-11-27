package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.Message;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.MessageRepository;
import hu.me.fdsz.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessageToUser(User currentUser) {
        return messageRepository.findAllMessagesToUser(currentUser);
    }


}
