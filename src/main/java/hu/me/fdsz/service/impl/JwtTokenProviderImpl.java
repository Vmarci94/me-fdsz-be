package hu.me.fdsz.service.impl;

import hu.me.fdsz.exception.InvalidTokenException;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.UserRepositroy;
import hu.me.fdsz.service.api.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class.getName());

    private SecretKey secretKey;

    private long validityInMilliseconds;

    private final UserRepositroy userRepositroy;

    @Autowired
    public JwtTokenProviderImpl(UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
    }

    @PostConstruct
    protected void init() {
        //creates a spec-compliant secure-random key:
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512
        validityInMilliseconds = 3600000;
    }

    @Override
    public String signin(String userEmail) {

        Claims claims = Jwts.claims().setSubject(userEmail);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(secretKey)
                .compact();

    }

    @Override
    public Authentication getAuthentication(String token) {
        //userEmail from token
        String userEmail = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        return userRepositroy.findByEmail(userEmail).map(currentUser ->
                new UsernamePasswordAuthenticationToken(currentUser, "", currentUser.getAuthorities())
        ).orElseThrow(() -> new UsernameNotFoundException("User '" + userEmail + "' not found"));
    }

    @Override
    public boolean validateToken(String token) throws InvalidTokenException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED, e);
        }
    }

    @Override
    public User getAuthenticatedUser() throws UsernameNotFoundException {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(authentication -> authentication.getPrincipal() instanceof User) //muszáj mert az API Object-et add vissza :/
                .map(authentication -> ((User) authentication.getPrincipal()))
//                .orElseThrow(() -> new InvalidTokenException("Nincs bejelentkezett felhasználó!", HttpStatus.UNAUTHORIZED));
                .orElse(userRepositroy.findById(4L).get());

    }

}
