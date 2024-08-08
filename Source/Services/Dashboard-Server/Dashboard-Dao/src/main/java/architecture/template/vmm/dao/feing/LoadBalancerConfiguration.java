package architecture.template.vmm.dao.feing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class LoadBalancerConfiguration {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  //endregion

  @Bean
  public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
    log.info("Configuring load balancer");
    return ServiceInstanceListSupplier
      .builder()
      .withBlockingDiscoveryClient()
      .build(context);
  }
}
