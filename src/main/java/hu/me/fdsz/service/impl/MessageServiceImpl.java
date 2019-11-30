package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.MailBoxDTO;
import hu.me.fdsz.dto.MessageDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.Message;
import hu.me.fdsz.model.User;
import hu.me.fdsz.model.enums.Role;
import hu.me.fdsz.repository.MessageRepository;
import hu.me.fdsz.repository.UserRepositroy;
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

    private final UserRepositroy userRepositroy;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper, UserService userService, UserRepositroy userRepositroy) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepositroy = userRepositroy;
    }

    @Override
    public Map<User, LinkedList<Message>> getTopMessagesFromUsersGroupedBySender() {
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
    public boolean deleteAllMessageFromCurrentUser() throws AuthenticationException {
        User currentUser = userService.getCurrentUser().orElseThrow(AuthenticationException::new);
        if (currentUser.getRole() != Role.ADMIN) {
            // egyenlőre csak a user tudja törölni az összes üzenetét, és ez most végig törli.
            // ha lesz idő akkor jó lenne megoldani az inboxot is.
            messageRepository.deleteALlBySender(currentUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Message> add(MessageDTO messageDTO) throws AuthenticationException {
        User sender = userService.getCurrentUser().orElseThrow(AuthenticationException::new);
        User reciever = userRepositroy.findById(messageDTO.getReciever().getId()).orElseThrow(EntityNotFoundException::new);
        Message newMessage = new Message();
        newMessage.setMessageContent(messageDTO.getMessage());
        newMessage.setSender(sender);
        newMessage.setReciever(reciever);
        return Optional.ofNullable(messageRepository.save(newMessage));
    }

}
