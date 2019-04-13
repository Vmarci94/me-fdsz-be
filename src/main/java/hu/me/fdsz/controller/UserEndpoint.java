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

@RestController
@RequestMapping(value = "/users")
public class UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody UserDTO userForm, HttpServletResponse response) {
        if (userService.signup(userForm) != null) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "${UserEndpoint.signin}")
    public void signin(@RequestBody UserDTO userDTO, HttpServletResponse response) throws LoginException {
        if (userService.signin(userDTO)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @GetMapping(value = "/get-default-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public JWTTokenDTO getDefaultToken() {
        JWTTokenDTO result = new JWTTokenDTO();
        result.setToken(userService.createDefaultToken());
        return result;
    }

}
