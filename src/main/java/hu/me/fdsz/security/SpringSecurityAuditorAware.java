package hu.me.fdsz.security;

import hu.me.fdsz.model.User;
import hu.me.fdsz.service.api.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SpringSecurityAuditorAware(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        User currentUser = jwtTokenProvider.getAuthenticatedUser();

        return Optional.of(currentUser);
    }
}
