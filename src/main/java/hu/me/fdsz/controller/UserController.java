package hu.me.fdsz.controller;

import hu.me.fdsz.model.dto.JWTTokenDTO;
import hu.me.fdsz.model.dto.UserDTO;
import hu.me.fdsz.model.entities.User;
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
import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;


    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    // Bejelentkezés
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTTokenDTO> signin(@RequestBody UserDTO userDTO) throws LoginException {
        return new ResponseEntity<>(
                new JWTTokenDTO(userService.signin(userDTO.getEmail(), userDTO.getPassword())),
                HttpStatus.OK);
    }

    // Regisztráció
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userForm) throws Exception {
        return Optional.of(userService.signup(modelMapper.map(userForm, User.class)))
                .map(user -> new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> result = userService.getAllUsers().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping(value = "/get-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserById(long userId) throws EntityNotFoundException {
        return userService.getUserById(userId)
                .map(user -> new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.OK))
                .orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping(value = "/get-current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUsername() throws AuthenticationException {
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
    public ResponseEntity<List<UserDTO>> searchUserByName(String searchTerm) {
        return new ResponseEntity<>(userService.searchUserByName(searchTerm)
                .stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
