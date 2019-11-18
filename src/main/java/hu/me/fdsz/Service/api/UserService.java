package hu.me.fdsz.Service.api;

import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;

@Service
public interface UserService {

    List<UserDTO> getAllUsers();

    /**
     * Regisztráció, és token generálás
     * @param userForm az új felhasználó adatai
     * @return a már perzisztált felhasználó adatai
     */
    UserDTO signup(UserDTO userForm) throws Exception;

    JWTTokenDTO signin(UserDTO userDTO) throws LoginException;

    UserDTO getCurrentUser();

    ResponseEntity<HttpStatus> updateUserData(UserDTO userDTO);

    List<UserDTO> findClientUsersByName(String fullName);

    List<UserDTO> searchUserByName(String searchTerm);
}
