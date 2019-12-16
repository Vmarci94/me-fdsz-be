package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.dto.MailBoxDTO;
import hu.me.fdsz.model.dto.UserDTO;
import hu.me.fdsz.model.entities.Message;
import hu.me.fdsz.model.entities.User;
import hu.me.fdsz.model.enums.Role;
import hu.me.fdsz.repository.MessageRepository;
import hu.me.fdsz.repository.UserRepository;
import hu.me.fdsz.service.api.MessageService;
import hu.me.fdsz.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper, UserService userService,
                              UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    private Map<User, LinkedList<Message>> getTopMessagesFromUsersGroupedBySender() {
        return messageRepository.findAllByReciever_RoleIsOrderByCreatedDate(Role.ADMIN).stream()
                .collect(Collectors.groupingBy(Message::getSender, Collectors.collectingAndThen(Collectors.toList(),
                        messages -> messages.stream().sorted((m1, m2) -> m2.getCreatedDate().compareTo(m1.getCreatedDate()))
                                .collect(Collectors.toCollection(LinkedList::new)))));
    }

    @Override
    public List<MailBoxDTO> getMailboxContent() {
        List<MailBoxDTO> result = new ArrayList<>();
        getTopMessagesFromUsersGroupedBySender().forEach((user, messageList) -> {
            MailBoxDTO.MailBoxDTOBuilder resultElemBuilder = MailBoxDTO.builder();
            Message lastMessage = messageList.getFirst();
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            result.add(resultElemBuilder
                    .sender(userDTO)
                    .readed(lastMessage.isReaded())
                    .incommindDate(lastMessage.getCreatedDate())
                    .topMessage(lastMessage.getMessageContent())
                    .build());
        });
        return result.stream().sorted((mb1, mb2) -> mb2.getIncommindDate().compareTo(mb1.getIncommindDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Message> add(Long userId, String messageContent) throws AuthenticationException {
        User sender = userService.getCurrentUser().orElseThrow(AuthenticationException::new);
        User reciever = userId != null ?
                userRepository.findById(userId).orElseThrow(EntityNotFoundException::new)
                :
                userService.getDefaultAdmin();
        Message newMessage = new Message();
        newMessage.setMessageContent(messageContent);
        newMessage.setSender(sender);
        newMessage.setReciever(reciever);
        return Optional.ofNullable(messageRepository.save(newMessage));
    }

}
