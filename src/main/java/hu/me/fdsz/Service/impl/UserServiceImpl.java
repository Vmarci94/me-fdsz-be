package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.Service.api.UserService;
import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.User;
import hu.me.fdsz.model.enums.Role;
import hu.me.fdsz.repository.UserRepositroy;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        return StreamSupport.stream(userRepositroy.findAll().spliterator(), false)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO signup(UserDTO userForm) throws Exception {
//        TypeMap<UserDTO, User> typeMap = modelMapper.createTypeMap(UserDTO.class, User.class);
//
//        typeMap.addMapping(
//                src -> Stream.of(src.getTitle(), src.getFirstName(), src.getSecoundName())
//                        .filter(Objects::nonNull)
//                        .collect(Collectors.joining()),
//                User::setFullName);

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
        UserDTO userDTO = modelMapper.map(jwtTokenProvider.getUser(), UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public UserDTO updateUserData(UserDTO userDTO) {
        logger.error("Implement me!");
        User currentUser = jwtTokenProvider.getUser();
        UserDTO modifiedUser = modelMapper.map(userRepositroy.save(currentUser), UserDTO.class);
        return modifiedUser;
    }

    @Override
    public List<UserDTO> getAllClientUser() {
        return userRepositroy.findAllByRole(Role.CLIENT)
                .map(userList -> userList.stream().map(user -> modelMapper.map(user, UserDTO.class)))
                .orElseThrow(EntityNotFoundException::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findClientUsersByName(String fullName) {
        return userRepositroy.findAllByFullName(fullName)
                .map(userList -> userList.stream().map(user -> modelMapper.map(user, UserDTO.class)))
                .orElseThrow(EntityNotFoundException::new)
                .collect(Collectors.toList());
    }

}
