package hu.me.fdsz.service.api;

import hu.me.fdsz.dto.JWTTokenDTO;
import hu.me.fdsz.dto.MessageDTO;
import hu.me.fdsz.dto.UserDTO;
import hu.me.fdsz.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import javax.security.auth.login.LoginException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

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

    Optional<UserDTO> getCurrentUserWithoutPassword();

    Optional<User> getCurrentUser();

    User updateUserData(UserDTO userDTO, MultipartFile multipartFile) throws AuthenticationException, AccessDeniedException;

    List<UserDTO> findClientUsersByName(String fullName);

    List<UserDTO> searchUserByName(String searchTerm);

    List<MessageDTO> getMessageToCurrentUser() throws AuthenticationException;

    List<MessageDTO> getMessageToUser(User user);

    List<MessageDTO> getMessageToUser(long userId);

    UserDTO getUserById(long userId);

    User getDefaultAdmin();

}
