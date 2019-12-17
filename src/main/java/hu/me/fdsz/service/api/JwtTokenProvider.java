package hu.me.fdsz.service.api;

import hu.me.fdsz.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface JwtTokenProvider {

    String createTokenWithEmail(String userEmail);

    Authentication getAuthentication(String token);

    Optional<User> getAuthenticatedUser() throws UsernameNotFoundException;

    boolean validateToken(String token);

}
