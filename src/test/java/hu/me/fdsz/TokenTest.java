package hu.me.fdsz;

import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.Service.impl.JwtTokenProviderImpl;
import hu.me.fdsz.model.User;
import hu.me.fdsz.security.JwtTokenFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TokenTest {

    private JwtTokenProvider jwtTokenProvider;
    private JwtTokenFilter jwtTokenFilter;

    @BeforeEach
    public void init() {
        jwtTokenProvider = mock(JwtTokenProviderImpl.class);
        jwtTokenFilter = mock(JwtTokenFilter.class);
    }

    @Test
    public void validToken_test(){
        when(jwtTokenFilter.doFilter())
        when(jwtTokenProvider.validateToken("jotoken")).thenReturn(true);
        Authentication authentication = new UsernamePasswordAuthenticationToken(new User(), "", org.springframework.security.core.userdetails.User
                .withUsername("test_user").build().getAuthorities());
        when(jwtTokenProvider.getAuthentication("jotoken")).thenReturn(authentication);
    }

}
