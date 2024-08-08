package architecture.template.vmm.beans;


import architecture.template.vmm.config.CustomWebFilter;
import architecture.template.vmm.filters.AuthFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayBeans {

  //region Dependencies
  private final AuthFilter authFilter;
  //endregion

  //region Ctor
  public GatewayBeans(AuthFilter authFilter) {
    this.authFilter = authFilter;
  }
  //endregion

  @Bean
  @Profile(value = "oauth2")
  public RouteLocator routeLocatorOauth2(RouteLocatorBuilder builder) {
    return builder
      .routes()
      .route(route -> route
        .path("/Portal-Server/**")
        .filters(filter -> {
          filter.filter(this.authFilter);
//          filter.filter(new CustomWebFilter());
          return filter;
        })
        .uri("lb://Portal-Server")
      )
      .route(route -> route
        .path("/Dashboard-Server/**")
        .uri("lb://Dashboard-Server")
      )
      .build();
  }
}
