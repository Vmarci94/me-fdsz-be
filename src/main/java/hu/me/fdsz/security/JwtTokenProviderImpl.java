package hu.me.fdsz.security;

import hu.me.fdsz.Service.api.JwtTokenProvider;
import hu.me.fdsz.model.User;
import hu.me.fdsz.repository.UserRepositroy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class.getName());

    private SecretKey secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;

    private final MyUserDetailsService myUserDetailsService; //FIXME ez ne maradjon itt, ha lehet csináljuk meg a nagy useres megoldást!

    private final UserRepositroy userRepositroy;

    @Autowired
    public JwtTokenProviderImpl(MyUserDetailsService myUserDetailsService, UserRepositroy userRepositroy) {
        this.myUserDetailsService = myUserDetailsService;
        this.userRepositroy = userRepositroy;
    }

    @PostConstruct
    protected void init() {
        //creates a spec-compliant secure-random key:
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512
    }

    @Override
    public String createToken(String userEmail) {

        Claims claims = Jwts.claims().setSubject(userEmail);
//      FIXME: claims.put("auth", roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList()));
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(secretKey)
                .compact();

    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Expired or invalid JWT token" + HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
        return false;
    }

    public User getUser() {
        String userEmail = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new IllegalStateException("Nincs bejelentkezett user!"))
                .getName();
        return userRepositroy.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException(userEmail + " is not found!"));
    }

}
