package hu.me.fdsz.security;

import hu.me.fdsz.repository.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {


    private final UserRepositroy userRepositroy;

    @Autowired
    public MyUserDetails(UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositroy.findByUsername(username)
                .map(currentUser -> org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(currentUser.getPassword())//
                .authorities(currentUser.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build()).orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }

}
