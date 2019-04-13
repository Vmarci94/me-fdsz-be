package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.UserDTO;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO signup(UserDTO userForm);

    boolean signin(UserDTO userDTO) throws LoginException;

    String createDefaultToken();

}
