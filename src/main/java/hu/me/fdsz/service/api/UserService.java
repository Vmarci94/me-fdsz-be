package hu.me.fdsz.service.api;

import hu.me.fdsz.model.dto.MessageDTO;
import hu.me.fdsz.model.dto.UserDTO;
import hu.me.fdsz.model.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import javax.security.auth.login.LoginException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getAllUsers();

    /**
     * Regisztráció, és token generálás
     * @param userForm az új felhasználó adatai
     * @return a már perzisztált felhasználó adatai
     */
    User signup(User userForm) throws Exception;

    String signin(String userEmail, String userPassword) throws IllegalArgumentException, LoginException;

    Optional<UserDTO> getCurrentUserWithoutPassword();

    Optional<User> getCurrentUser();

    Optional<User> updateUserData(UserDTO userDTO, MultipartFile multipartFile) throws AuthenticationException, AccessDeniedException;

    List<User> searchUserByName(String searchTerm);

    List<MessageDTO> getMessageToCurrentUser() throws AuthenticationException;

    List<MessageDTO> getMessageToUser(User user);

    List<MessageDTO> getMessageToUser(long userId);

    Optional<User> getUserById(long userId);

    User getDefaultAdmin();

}
