package hu.me.fdsz.security;

import hu.me.fdsz.repository.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private final UserRepositroy userRepositroy;

    @Autowired
    public MyUserDetailsService(UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepositroy.findByEmail(userEmail)
                .map(currentUser -> org.springframework.security.core.userdetails.User
                        .withUsername(userEmail) // TODO: Egyenlőre így jó lesz, bár elég zavaró
                        .password(currentUser.getPassword())//
                        .authorities(currentUser.getRoles())//
                        .accountExpired(false)//
                        .accountLocked(false)//
                        .credentialsExpired(false)//
                        .disabled(false)//
                        .build()).orElseThrow(() -> new UsernameNotFoundException("User '" + userEmail + "' not found"));
    }

}
