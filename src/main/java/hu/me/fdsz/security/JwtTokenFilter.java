package hu.me.fdsz.security;

import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.Utils.Util;
import hu.me.fdsz.exception.CustomExceptionDTO;
import hu.me.fdsz.exception.InvalidTokenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

// We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;


    JwtTokenFilter(JwtTokenProvider jwtTokenProviderImpl) {
        this.jwtTokenProvider = jwtTokenProviderImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            resolveToken(httpServletRequest)
                    .ifPresent(token -> {
                        if (jwtTokenProvider.validateToken(token)) {
                            Authentication auth = jwtTokenProvider.getAuthentication(token);
                            SecurityContextHolder.getContext().setAuthentication(auth);
                        }
                    });
        } catch (InvalidTokenException ite) {
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(ite.getStatusCode(), Util.convertObjectToJson(new CustomExceptionDTO(ite)));
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Optional<String> resolveToken(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader("Authorization"))
                .filter(bearerToken -> bearerToken.startsWith("Bearer "))
                .map(bearerToken -> bearerToken.substring(7));
    }


}
