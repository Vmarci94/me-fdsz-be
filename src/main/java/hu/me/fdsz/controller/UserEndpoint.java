package hu.me.fdsz.controller;

import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserEndpoint {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserEndpoint(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    //    Regisztráció
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userForm) throws Exception {
        return Optional.of(userService.signup(userForm))
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser() {
        return userService.getAllUsers();
    }

    //Bejelentkezés
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/get-client-users-by-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getClientUsersByName(String fullName) {
        return userService.findClientUsersByName(fullName);
    }

    @GetMapping(value = "/get-by-id")
    public UserDTO getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping(value = "/get-current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsername() throws AuthenticationException {
        return userService.getCurrentUserWithoutPassword()
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseThrow(AuthenticationException::new);
    }

    @PostMapping(value = "/update-user-data", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDTO> updateUserData(@RequestPart(name = "user") UserDTO userDTO,
                                                  @RequestPart(name = "image", required = false) MultipartFile multipartFile)
            throws AccessDeniedException, AuthenticationException {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(userService.updateUserData(userDTO, multipartFile).map(user -> modelMapper.map(user, UserDTO.class))
                .orElseThrow(() -> new AccessDeniedException("Nincs jogosulstság a módosításra.")), responseHeader, HttpStatus.OK);
    }

    @GetMapping(value = "/search-users-by-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> searchUserByName(String searchTerm) {
        return userService.searchUserByName(searchTerm);
    }

}
