package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.User;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO signIn(UserDTO userForm);

    boolean login(UserDTO userDTO) throws LoginException;

    String createDefaultToken();

}
