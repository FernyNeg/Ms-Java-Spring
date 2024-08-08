package architecture.template.vmm.dao.feing;

import architecture.template.vmm.dto.user.UserDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Portal-Server")
@LoadBalancerClient(name = "Portal-Server", configuration = LoadBalancerConfiguration.class)
public interface UserFeingClient {

  @PostMapping(path = "/Portal-Server/User/SaveNewUser")
  String postToSaveUser(@RequestBody UserDto user);

}
