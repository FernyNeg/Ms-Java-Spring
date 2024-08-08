package architecture.template.vmm.helpers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtHelper {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Value("${application.jwt.secret}")
  private String jwtSecret;
  //endregion

  public String createToken(String username) {
    final var now = new Date();
    final var expirationDate = new Date(now.getTime() + (3600 * 1000));
    return Jwts
      .builder()
      .setSubject(username)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(expirationDate)
      .signWith(this.getSecretKey())
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      final var expirationDate = this.getExpirationDate(token);
      log.info("Expiration date: {}",expirationDate);
      return expirationDate.after(new Date());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jwt invalid");
    }

  }

  private Date getExpirationDate(String token) {
    return this.getClaimsFromToken(token, Claims::getExpiration);
  }

  private <T> T getClaimsFromToken(String token, Function<Claims, T> resolver) {
    return resolver.apply(this.signToken(token));
  }

  private Claims signToken(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(this.getSecretKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(this.jwtSecret.getBytes(StandardCharsets.UTF_8));
  }
}
