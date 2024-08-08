package architecture.template.vmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"architecture.template.vmm.entity"})
@ComponentScan({"architecture.template.vmm.*"})
@EnableDiscoveryClient
public class PortalServer {

  public static void main(String[] args) {
    SpringApplication.run(PortalServer.class, args);
  }

}
