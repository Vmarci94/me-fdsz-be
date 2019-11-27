package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.MailBoxDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.Message;
import hu.me.fdsz.model.User;
import hu.me.fdsz.model.enums.Role;
import hu.me.fdsz.repository.MessageRepository;
import hu.me.fdsz.service.api.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Map<User, LinkedList<Message>> getTopMessagesFromUsersGroupedBySender() {
        return messageRepository.findAllByReciever_RoleIsOrderByCreatedDateAsc(Role.ADMIN).stream()
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

}
