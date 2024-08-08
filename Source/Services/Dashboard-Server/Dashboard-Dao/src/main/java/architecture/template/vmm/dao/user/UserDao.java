package architecture.template.vmm.dao.user;

import architecture.template.vmm.constants.SystemConstants;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import static architecture.template.vmm.messages.ExceptionsConstants.ERROR_TO_AUTH_USER;

@Repository
public class UserDao {

  //region Dependencies
  private final UserRepository userRepository;
  //endregion

  //region Ctor
  public UserDao(
    UserRepository userRepository
  ) {
    this.userRepository = userRepository;
  }
  //endregion

  //region Getters
  public UserEntity validateByUserInfo(String user) {
    return userRepository.findByEmailAndFdlOrUsernameAndFdlOrCellphoneAndFdl(
      user, SystemConstants.DEFAULT_FDL_ENABLED,
      user, SystemConstants.DEFAULT_FDL_ENABLED,
      user, SystemConstants.DEFAULT_FDL_ENABLED
    ).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_TO_AUTH_USER));
  }
  //endregion

}
