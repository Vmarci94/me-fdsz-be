package hu.me.fdsz.controller;

import hu.me.fdsz.Service.api.UserService;
import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    //Regisztráció
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody UserDTO userForm, HttpServletResponse response) throws Exception {
        response.setStatus(Optional.of(userService.signup(userForm))
                .map(userDTO -> HttpServletResponse.SC_ACCEPTED)
                .orElse(HttpServletResponse.SC_FORBIDDEN));

    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser() {
        return userService.getAllUsers();
    }

    //Bejelentkezés
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "${UserEndpoint.signin}")
    public JWTTokenDTO signin(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        JWTTokenDTO result = null;
        try {
            result = userService.signin(userDTO);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch (LoginException le) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return result;
    }

    @GetMapping(value = "/get-currnet-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserName() {
        return userService.getCurrentUser();
    }

    @PostMapping(value = "/update-user-data")
    public UserDTO updateUserData(@RequestBody UserDTO userDTO) {
        return userService.updateUserData(userDTO);
    }

}
