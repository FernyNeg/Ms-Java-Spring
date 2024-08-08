package architecture.template.vmm.imp.user.operations;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.user.UserDao;

@Service
@Transactional
public class UpdateUserService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserDao userDao;
  private final SearchUserService searchUserService;
  //endregion

  //region Ctor
  public UpdateUserService(final BCryptPasswordEncoder bCryptPasswordEncoder, final UserDao userDao, final SearchUserService searchUserService) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userDao = userDao;
    this.searchUserService = searchUserService;
  }
  //endregion

  public void handleUpdate(UserDto user) {
    log.info("Updating user: {}", user.getUuid());
    var userEntity = searchUserService.handleGetByUuid(user.getUuid());
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    BeanUtils.copyProperties(user, userEntity, "id");
    log.info("Saving user data");
    userDao.updateUser(userEntity);
  }

}
