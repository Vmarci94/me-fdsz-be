package hu.me.fdsz.Service.api;

import hu.me.fdsz.model.User;
import org.springframework.security.core.Authentication;

public interface JwtTokenProvider {

    String createToken(String userEmail);

    Authentication getAuthentication(String token);

    User getUser();

    boolean validateToken(String token);

}
