package architecture.template.vmm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class CustomWebFilter implements WebFilter {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return exchange.getSession().flatMap(session -> {
      session.getAttributes().put("userId", "id123");
      log.info("User in session: {}", Optional.ofNullable(session.getAttribute("userId")));
      return chain.filter(exchange);
    });
  }

}
