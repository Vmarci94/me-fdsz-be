package hu.me.fdsz.service.impl;

import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.User;
import hu.me.fdsz.model.enums.Role;
import hu.me.fdsz.repository.UserRepositroy;
import hu.me.fdsz.service.api.JwtTokenProvider;
import hu.me.fdsz.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Autowired
    public UserServiceImpl(UserRepositroy userRepositroy, ModelMapper modelMapper, JwtTokenProvider jwtTokenProvider) {
        this.userRepositroy = userRepositroy;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
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
        if (userRepositroy.existsByEmailAndUserName(newUser.getEmail(), newUser.getUserName())) {
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
    public UserDTO getCurrentUser() {
        UserDTO userDTO = modelMapper.map(jwtTokenProvider.getAuthenticatedUser(), UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public ResponseEntity<HttpStatus> updateUserData(UserDTO userDTO) {
        if (jwtTokenProvider.getAuthenticatedUser().getRole().equals(Role.ADMIN)) {
            return userRepositroy.findByUserName(userDTO.getUserName())
                    .map(user -> {
                        User mUser = modelMapper.map(userDTO, User.class);

                        user.setTitle(Optional.ofNullable(mUser.getTitle()).orElse(user.getTitle()));
                        user.setFirstName(Optional.ofNullable(mUser.getFirstName()).orElse(user.getFirstName()));
                        user.setSecoundName(Optional.ofNullable(mUser.getSecoundName()).orElse(user.getSecoundName()));

                        user.setEmail(Optional.ofNullable(mUser.getEmail()).orElse(user.getEmail()));
                        user.setPhoneNumber(Optional.ofNullable(mUser.getPhoneNumber()).orElse(user.getPhoneNumber()));
                        user.setLocation(Optional.ofNullable(mUser.getLocation()).orElse(user.getLocation()));

                        user.setRole(Optional.ofNullable(mUser.getRole()).orElse(user.getRole()));

                        return userRepositroy.save(user) != null ?
                                new ResponseEntity<HttpStatus>(HttpStatus.OK)
                                :
                                new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
                    })
                    .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
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

}
