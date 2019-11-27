package hu.me.fdsz.controller;

import hu.me.fdsz.dto.MailBoxDTO;
import hu.me.fdsz.dto.MessageDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.User;
import hu.me.fdsz.service.api.MessageService;
import hu.me.fdsz.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping(value = "/messages")
public class MessagesEndpoint {

    private final UserService userService;

    private final MessageService messageService;

    private final ModelMapper modelMapper;

    @Autowired
    public MessagesEndpoint(UserService userService, MessageService messageService, ModelMapper modelMapper) {
        this.userService = userService;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/to-current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessagesToCurrentUser() throws AuthenticationException {
        return userService.getMessageToCurrentUser();
    }

    @GetMapping(value = "/to-user")
    public List<MessageDTO> getMessagesToUser(UserDTO userDTO) {
        return userService.getMessageToUser(modelMapper.map(userDTO, User.class));
    }

    @GetMapping(value = "/inbox")
    public List<MailBoxDTO> getUsers() {
        return messageService.getMailboxContent();
    }

}
