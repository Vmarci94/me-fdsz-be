package hu.me.fdsz.controller;

import hu.me.fdsz.repository.UserRepositroy;
import hu.me.fdsz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserEndpoint {

    private final UserRepositroy userRepositroy;

    @Autowired
    public UserEndpoint(UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
    }

    @PostMapping(value = "/add-new-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User addNewUser(String name){
        User newUser = new User(name);
        return userRepositroy.save(newUser);
    }

}
