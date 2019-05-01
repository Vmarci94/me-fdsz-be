package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.Service.api.UserService;
import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.Role;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.UserRepositroy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

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
        User newUser = modelMapper.map(userForm, User.class);
        if (userRepositroy.existsByEmailAndUserName(newUser.getEmail(), newUser.getUserName())) {
            //ha létezik már ilyen regisztráció, akkor hibát dobunk
            throw new Exception("Ezekkel az adatokkal már regisztráltak!"); //FIXME csináljunk tisztességes kivételkezelést
        } else {
            newUser.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
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
        User currentUser = jwtTokenProvider.getUser();
        currentUser.setPersonalName(userDTO.getPersonalName());
        UserDTO modifiedUser = modelMapper.map(userRepositroy.save(currentUser), UserDTO.class);
        return modifiedUser;
    }

}
