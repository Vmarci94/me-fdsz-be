package hu.me.fdsz.Service.api;

import hu.me.fdsz.model.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface JwtTokenProvider {

    String createToken(String username, List<Role> roles);

    Authentication getAuthentication(String token);

    String getUsername(String token);

    String resolveToken(HttpServletRequest req);

    boolean validateToken(String token);

}
