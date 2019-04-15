package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.UserDTO;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    /**
     * Regisztráció, és token generálás
     * @param userForm az új felhasználó adatai
     * @return a már perzisztált felhasználó adatai
     */
    UserDTO signup(UserDTO userForm);

    boolean signin(UserDTO userDTO) throws LoginException;

}
