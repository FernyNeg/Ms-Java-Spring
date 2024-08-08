package architecture.template.vmm.config;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class SessionFilter implements WebFilter {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return exchange.getSession().flatMap(session -> {
      // Leer el usuario de la sesión
      String userId = (String) session.getAttribute("userId");
      if (userId == null) {
        // Escribir el usuario en la sesión si no existe
        session.getAttributes().put("userId", "id123");
      }
      return chain.filter(exchange);
    });
  }
}
