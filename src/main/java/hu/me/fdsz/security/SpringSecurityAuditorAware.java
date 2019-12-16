package hu.me.fdsz.security;

import hu.me.fdsz.model.entities.User;
import hu.me.fdsz.service.api.UserService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    private final UserService userService;

    public SpringSecurityAuditorAware(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        return userService.getCurrentUser();
    }
}
