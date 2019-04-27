package hu.me.fdsz.Service.api;

import hu.me.fdsz.model.User;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtTokenProvider {

    String createToken(String userEmail);

    Authentication getAuthentication(String token);

    String getUserEmail(String token);

    User getUser();

    String resolveToken(HttpServletRequest req);

    boolean validateToken(String token);

}
