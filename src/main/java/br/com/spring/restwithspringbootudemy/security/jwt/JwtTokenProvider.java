package br.com.spring.restwithspringbootudemy.security.jwt;

import br.com.spring.restwithspringbootudemy.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    public void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userName, List<String> roles){
        var currentDate = new Date();
        var validityDate = new Date(currentDate.getTime() + validityInMilliseconds);
        var claims = Jwts.claims().setSubject(userName);
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(validityDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public Authentication getAuthentication(String token){
        var userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "" ,userDetails.getAuthorities());
    }

    private String getUserName(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        var bearerToken = httpServletRequest.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer "))
            return null;
        return bearerToken.split("Bearer ")[1];
    }
    public boolean validateToken(String token){
        try {
            var claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }
        catch (Exception e){
            throw new InvalidJwtAuthenticationException("Invalid or expired token");
        }
    }
}
