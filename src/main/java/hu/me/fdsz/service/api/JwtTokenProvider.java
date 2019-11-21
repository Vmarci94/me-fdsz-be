package hu.me.fdsz.service.api;

import hu.me.fdsz.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenProvider {

    String signin(String userEmail);

    /**
     * create {@link org.springframework.security.core.userdetails.User} from {@link User}
     *
     * @return {@link UsernamePasswordAuthenticationToken}
     */
    Authentication getAuthentication(String token);

    User getAuthenticatedUser() throws UsernameNotFoundException;

    boolean validateToken(String token);

}
