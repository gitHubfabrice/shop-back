package com.fatechnologies.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private static final String SECRET ="bF7449XF5lc5iz5sW5Y5suX4d7ok4P7jbF7449XF5lc5iz5sW5Y5suX4d7ok4P7j";
    private static final long VALIDITY_TOKEN_IN_SECOND = 3600;
    private static final long VALIDITY_TOKEN_IN_SECOND_FOR_REMEMBER_ME = 21600;
    private Key key;
    private static final String AUTHORITIES_KEY = "auth";
    private long tokenValidityInMilliseconds;
    private long tokenValidityInMillisecondsForRememberMe;
    private JwtParser jwtParser;


    public String createToken(Authentication authentication, boolean rememberMe){
        String authorities = authentication
                .getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = new Date().getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .toList();

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken){
        try {
            jwtParser.parseClaimsJws(authToken);
                return true;
            } catch (ExpiredJwtException e) {
            log.trace(String.valueOf(e));
            }
        return false;
    }

    @PostConstruct
    public void init(){

        String BASE64_SECRET = Base64.getEncoder().encodeToString(SECRET.getBytes(StandardCharsets.UTF_8));
        var keyByte = Decoders.BASE64.decode(BASE64_SECRET);
        this.tokenValidityInMillisecondsForRememberMe = 10000 * VALIDITY_TOKEN_IN_SECOND_FOR_REMEMBER_ME;
        this.tokenValidityInMilliseconds = 10000 * VALIDITY_TOKEN_IN_SECOND;
        key = Keys.hmacShaKeyFor(keyByte);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();

    }
}
