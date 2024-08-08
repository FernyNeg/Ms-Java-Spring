package architecture.template.vmm.imp.user.operations;

import architecture.template.vmm.user.UserDao;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_DISABLE;

@Service
@Transactional
public class DeleteUserService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserDao userDao;
  private final SearchUserService searchUserService;
  //endregion

  //region Ctor
  public DeleteUserService(final UserDao userDao, final SearchUserService searchUserService) {
    this.userDao = userDao;
    this.searchUserService = searchUserService;
  }
  //endregion

  //region Service
  public void handleDelete(UUID userId) {
    log.info("Getting user by uuid: {}", userId);
    var userToDelete = searchUserService.handleGetByUuid(userId);
    log.warn("Disabling user...");
    userToDelete.setFdl(DEFAULT_FDL_DISABLE);
    userDao.updateUser(userToDelete);
    log.info("Deleting correctly");
  }
  //endregion

}
