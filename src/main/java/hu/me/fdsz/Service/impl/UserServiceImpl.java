package hu.me.fdsz.Service.impl;

import hu.me.fdsz.Service.api.UserService;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.UserRepositroy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositroy userRepositroy;

    @Autowired
    public UserServiceImpl(UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(userRepositroy.findAll().spliterator(), false)
                .map(user -> new ModelMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO signIn(UserDTO userForm) {
        ModelMapper modelMapper = new ModelMapper();
        User newUser = modelMapper.map(userForm, User.class);
        return modelMapper.map(userRepositroy.save(newUser), UserDTO.class);
    }

    @Override
    public boolean login(UserDTO userDTO) throws LoginException {
        return userRepositroy.findByEmail(userDTO.getEmail())
                .map(user -> user.getPassword().equals(userDTO.getPassword()))
                .orElseThrow(() -> new LoginException("hibás adatok"));
    }

    @Override
    public String createDefaultToken() {
        Claims claims = Jwts.claims().setSubject("me-fdsz");
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
    }

}
