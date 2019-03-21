package hu.me.fdsz.controller;

import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.dto.UserFormDTO;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.UserRepositroy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/user")
public class UserEndpoint {

    private final UserRepositroy userRepositroy;

    @Autowired
    public UserEndpoint(UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
    }

    @PostMapping(value = "/add-new-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO addNewUser(@RequestBody UserFormDTO userForm) {
        ModelMapper modelMapper = new ModelMapper();
        User newUser = modelMapper.map(userForm, User.class);
        return modelMapper.map(userRepositroy.save(newUser), UserDTO.class);
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser() {
        return StreamSupport.stream(userRepositroy.findAll().spliterator(), false)
                .map(user -> new ModelMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());

    }

}
