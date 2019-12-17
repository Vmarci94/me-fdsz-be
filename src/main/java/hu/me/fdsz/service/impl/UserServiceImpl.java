package hu.me.fdsz.service.impl;

import hu.me.fdsz.model.dto.MessageDTO;
import hu.me.fdsz.model.dto.UserDTO;
import hu.me.fdsz.model.entity.User;
import hu.me.fdsz.model.enums.Role;
import hu.me.fdsz.repository.MessageRepository;
import hu.me.fdsz.repository.UserRepository;
import hu.me.fdsz.service.api.ImageService;
import hu.me.fdsz.service.api.JwtTokenProvider;
import hu.me.fdsz.service.api.UserService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final ImageService imageService;

    private final MessageRepository messageRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider, ImageService imageService, MessageRepository messageRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.imageService = imageService;
        this.messageRepository = messageRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User signup(User newUser) throws Exception {
        newUser.setFullName(Stream.of(newUser.getTitle(), newUser.getFirstName(), newUser.getSecoundName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" ")));
        if (userRepository.existsByEmailAndUsername(newUser.getEmail(), newUser.getUsername())) {
            //ha létezik már ilyen regisztráció, akkor hibát dobunk
            throw new Exception("Ezekkel az adatokkal már regisztráltak!"); //FIXME csináljunk tisztességes kivételkezelést
        } else {
            newUser.setRole(Role.CLIENT);
            userRepository.save(newUser);
        }
//        String token = jwtTokenProvider.signin(); //FIXME ezt még nem tudom minek
        return newUser;
    }

    @Override
    public String signin(String userEmail, String userPassword) throws IllegalArgumentException, LoginException {
        if (StringUtils.isAnyBlank(userEmail, userEmail)) {
            throw new IllegalArgumentException("rossz felhasználónév vagy jelszó");
        }
        return userRepository.findByEmail(userEmail.trim())
                .filter(user -> passwordEncoder.matches(userPassword, user.getPassword()))
                .map(user -> jwtTokenProvider.createTokenWithEmail(user.getEmail()))
                .orElseThrow(() -> new LoginException("hibás bejelentkezési adatok!"));
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
        return jwtTokenProvider.getAuthenticatedUser();
    }

    public User updateCurrentUserData(UserDTO userDTO, MultipartFile multipartFile) {
        User newUserDatas = modelMapper.map(userDTO, User.class);
        imageService.updateImage(newUserDatas, multipartFile);
        return userRepository.save(newUserDatas);
    }

    @Override
    public Optional<User> updateUserData(UserDTO userDTO, MultipartFile multipartFile) throws AuthenticationException, AccessDeniedException {
        User currentUser = getCurrentUser().orElseThrow(AuthenticationException::new);
        if (currentUser.getRole() == Role.ADMIN || currentUser.getEmail().equals(userDTO.getEmail())) {
            return userRepository.findByEmail(userDTO.getEmail()).map(oldUser -> {
                User inputUser = modelMapper.map(userDTO, User.class);
                if (multipartFile != null) {
                    imageService.updateImage(inputUser, multipartFile);
                }
                if (StringUtils.isEmpty(inputUser.getPassword())) {
                    inputUser.setPassword(oldUser.getPassword());
                }
                inputUser.setId(oldUser.getId());
                return userRepository.save(inputUser);
            });
        } else {
            throw new AccessDeniedException("Nincs jogosulstság a módosításra.");
        }
    }

    @Override
    public List<User> searchUserByName(String searchTerm) {
        return userRepository.findAllByFullNameContaining(searchTerm);
    }

    @Override
    public List<MessageDTO> getMessageToCurrentUser() throws AuthenticationException {
        return getCurrentUser().map(this::getMessageToUser).orElseThrow(AuthenticationException::new);
    }

    @Override
    public List<MessageDTO> getMessageToUser(User user) {
        return getMessageToUser(user.getId());
    }

    @Override
    public List<MessageDTO> getMessageToUser(long userId) {
        return userRepository.findById(userId).map(user -> messageRepository.findAllBySenderOrRecieverOrderByCreatedDate(user, user)
                .stream().map(message -> modelMapper.map(message, MessageDTO.class))
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User getDefaultAdmin() {
        //Ez most Alap Elek
        return userRepository.findById(4L).orElseThrow(EntityNotFoundException::new);
    }

}
