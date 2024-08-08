package architecture.template.vmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"architecture.template.vmm.*"})

public class DashboardServer {

  public static void main(String[] args) {
    SpringApplication.run(DashboardServer.class, args);
  }

}
