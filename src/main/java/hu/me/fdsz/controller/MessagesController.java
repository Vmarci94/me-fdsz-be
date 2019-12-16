package hu.me.fdsz.controller;

import hu.me.fdsz.model.dto.MailBoxDTO;
import hu.me.fdsz.model.dto.MessageDTO;
import hu.me.fdsz.service.api.MessageService;
import hu.me.fdsz.service.api.UserService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping(value = "/messages")
public class MessagesController {

    private final UserService userService;

    private final MessageService messageService;

    private final ModelMapper modelMapper;

    @Autowired
    public MessagesController(UserService userService, MessageService messageService, ModelMapper modelMapper) {
        this.userService = userService;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/to-current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessagesToCurrentUser() throws AuthenticationException {
        return userService.getMessageToCurrentUser();
    }

    @GetMapping(value = "/to-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessagesToUser(long userId) {
        return userService.getMessageToUser(userId);
    }

    @GetMapping(value = "/inbox", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MailBoxDTO> getUsers() {
        return messageService.getMailboxContent();
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createNewMessage(@RequestBody MessageDTO messageDTO) throws AuthenticationException {
        if (messageDTO == null || StringUtils.isBlank(messageDTO.getMessage())) {
            throw new IllegalArgumentException("hibás paraméterek!");
        }
        Long recieverId = messageDTO.getReciever() != null ? messageDTO.getReciever().getId() : null;
        return new ResponseEntity<>(messageService.add(recieverId, messageDTO.getMessage()).isPresent() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }

}
