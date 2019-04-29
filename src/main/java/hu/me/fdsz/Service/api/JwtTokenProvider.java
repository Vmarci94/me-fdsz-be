package hu.me.fdsz.Service.api;

import hu.me.fdsz.exception.InvalidTokenException;
import hu.me.fdsz.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface JwtTokenProvider {

    String signin(String userEmail);

    /**
     * create {@link org.springframework.security.core.userdetails.User} from {@link User}
     *
     * @return {@link UsernamePasswordAuthenticationToken}
     */
    Authentication getAuthentication(String token);

    User getUser() throws UsernameNotFoundException;

    boolean validateToken(String token) throws InvalidTokenException;

}
