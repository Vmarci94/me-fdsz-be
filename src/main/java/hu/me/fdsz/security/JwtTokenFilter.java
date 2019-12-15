package hu.me.fdsz.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.me.fdsz.exception.CustomExceptionDTO;
import hu.me.fdsz.exception.InvalidTokenException;
import hu.me.fdsz.service.api.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/*")
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    private static final String HEADER_ORIGIN = "Origin";

    JwtTokenFilter(JwtTokenProvider jwtTokenProviderImpl) {
        this.jwtTokenProvider = jwtTokenProviderImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        httpServletResponse.addHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader(HEADER_ORIGIN));
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpServletResponse.addHeader("Access-Control-Max-Age", "-1");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        httpServletResponse.addHeader("Authorization", "Access-Control-Allow-Headers");
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");

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
            httpServletResponse.sendError(ite.getStatusCode(), new ObjectMapper().writeValueAsString(new CustomExceptionDTO(ite)));
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Optional<String> resolveToken(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader("Authorization"))
                .filter(bearerToken -> bearerToken.startsWith("Bearer "))
                .map(bearerToken -> bearerToken.substring(7));
    }

}
