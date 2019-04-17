package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.Service.api.UserService;
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
                .map(user -> new ModelMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO signup(UserDTO userForm) {
        User newUser = userRepositroy.save(modelMapper.map(userForm, User.class));
        newUser.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        String token = jwtTokenProvider.createToken();
        return modelMapper.map(newUser, UserDTO.class);
    }

    @Override
    public boolean signin(UserDTO userDTO) throws LoginException {
        return userRepositroy.findByEmail(userDTO.getEmail())
                .map(user -> {
                    boolean result = user.getPassword().equals(userDTO.getPassword());
                    String token = jwtTokenProvider.createToken();
                    return result;
                })
                .orElseThrow(() -> new LoginException("hibás adatok"));
    }

    @Override
    public String createToken() {
        return jwtTokenProvider.createToken();
    }

}
