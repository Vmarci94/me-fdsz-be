package hu.me.fdsz.controller;

import hu.me.fdsz.Service.api.UserService;
import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO addNewUser(@RequestBody UserDTO userForm) {
        return userService.signIn(userForm);
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean login(@RequestBody UserDTO userDTO) throws LoginException {
        return userService.login(userDTO);
    }

    @GetMapping(value = "/get-default-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public JWTTokenDTO getDefaultToken(){
        JWTTokenDTO result = new JWTTokenDTO();
        result.setToken(userService.createDefaultToken());;
        return result;
    }

}
