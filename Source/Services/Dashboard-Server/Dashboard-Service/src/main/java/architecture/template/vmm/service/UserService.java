package architecture.template.vmm.service;

import architecture.template.vmm.dao.feing.UserFeingClient;
import architecture.template.vmm.dto.user.UserDto;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserFeingClient userFeingClient;
  //endregion

  //region Ctor
  public UserService(
    UserFeingClient userFeingClient
  ) {
    this.userFeingClient = userFeingClient;
  }
  //endregion

  //region Services
  public String saveNewUser(UserDto user) {
    log.info("Sending new user to save");
    return userFeingClient.postToSaveUser(user);
  }
  //endregion

}
