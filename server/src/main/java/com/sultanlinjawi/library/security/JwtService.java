// add JWT token generate and validate
package com.sultanlinjawi.library.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@Service
@Slf4j
public class JwtService {

    private final UserDetailsService userDetailsService;

    public static final long MILLIS_IN_HOUR = 60 * 60 * 1000;

    private String secretkey;

    @Value("${jwt.expiration:24}")
    private int jwtExpiration;

    public JwtService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            log.debug("Jwt secret key generator broken");
            secretkey = "my-32-character-ultra-secure-and-ultra-long-secret";
        }
    }

    public String generateToken(UserDetails userDetails) {
        // TODO: Remove claims stuff later and check if it still works
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + MILLIS_IN_HOUR * jwtExpiration))
                .and()
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public UserDetails validateToken(String token) {
        var username = extractUsername(token);
        return userDetailsService.loadUserByUsername(username);
    }

    private String extractUsername(String token) {
        // TODO: Handle jwt expiration exception
        var claims =
                Jwts.parser()
                        .verifyWith(getSigningKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
        return claims.getSubject();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
