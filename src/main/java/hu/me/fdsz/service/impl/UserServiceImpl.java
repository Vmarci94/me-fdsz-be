package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.MessageDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.User;
import hu.me.fdsz.model.enums.Role;
import hu.me.fdsz.repository.MessageRepository;
import hu.me.fdsz.repository.UserRepositroy;
import hu.me.fdsz.service.api.ImageService;
import hu.me.fdsz.service.api.JwtTokenProvider;
import hu.me.fdsz.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private final UserRepositroy userRepositroy;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final ImageService imageService;

    private final MessageRepository messageRepository;

    @Autowired
    public UserServiceImpl(UserRepositroy userRepositroy, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider, ImageService imageService, MessageRepository messageRepository) {
        this.userRepositroy = userRepositroy;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.imageService = imageService;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepositroy.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO signup(UserDTO userForm) throws Exception {
        User newUser = modelMapper.map(userForm, User.class);
        newUser.setFullName(Stream.of(userForm.getTitle(), userForm.getFirstName(), userForm.getSecoundName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" ")));
        if (userRepositroy.existsByEmailAndUsername(newUser.getEmail(), newUser.getUsername())) {
            //ha létezik már ilyen regisztráció, akkor hibát dobunk
            throw new Exception("Ezekkel az adatokkal már regisztráltak!"); //FIXME csináljunk tisztességes kivételkezelést
        } else {
            newUser.setRole(Role.CLIENT);
            userRepositroy.save(newUser);
        }
//        String token = jwtTokenProvider.signin(); //FIXME ezt még nem tudom minek
        return modelMapper.map(newUser, UserDTO.class);
    }

    @Override
    public JWTTokenDTO signin(UserDTO userDTO) throws LoginException {
        return userRepositroy.findByEmail(userDTO.getEmail())
                .filter(currentUser -> currentUser.getPassword().equals(userDTO.getPassword()))
                .map(user -> new JWTTokenDTO(jwtTokenProvider.signin(user.getEmail())))
                .orElseThrow(() -> new LoginException("hibás adatok"));
    }

    @Override
    public Optional<UserDTO> getCurrentUserWithoutPassword() {
        return getCurrentUser().map(user -> {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.setPassword(null);
            return userDTO;
        });
    }

    @Override
    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(jwtTokenProvider.getAuthenticatedUser());
    }

    public User updateCurrentUserData(UserDTO userDTO, MultipartFile multipartFile) {
        User newUserDatas = modelMapper.map(userDTO, User.class);
        imageService.updateImage(newUserDatas, multipartFile);
        return userRepositroy.save(newUserDatas);
    }

    @Override
    public boolean updateUserData(UserDTO userDTO, MultipartFile multipartFile) {
        Optional<User> currentUser = getCurrentUser().filter(user -> user.getRole() == Role.ADMIN);
        if (currentUser.isPresent()) {
            //van jogosultság a frissítéshez
            //megkeressük a frissítendő user-t
            Optional<User> oldUser = userRepositroy.findByEmail(userDTO.getEmail());
            if (oldUser.isPresent()) {
                //ha létezik a módosítandó user akkor örülünk, és módosítjuk
                User inputUser = modelMapper.map(userDTO, User.class);
                imageService.updateImage(inputUser, multipartFile);
                userRepositroy.save(inputUser);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserDTO> findClientUsersByName(String fullName) {
        return userRepositroy.findAllByFullName(fullName)
                .map(userList -> userList.stream().map(user -> modelMapper.map(user, UserDTO.class)))
                .orElseThrow(EntityNotFoundException::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> searchUserByName(String searchTerm) {
        return userRepositroy.findAllByFullNameContaining(searchTerm)
                .map(users -> users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<MessageDTO> getMessageToCurrentUser() {
        return getCurrentUser().map(currentUser ->
                messageRepository.findAllMessagesToUser(currentUser).stream()
                        .map(message -> modelMapper.map(message, MessageDTO.class))
                        .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

}
