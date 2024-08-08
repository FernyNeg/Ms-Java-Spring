package architecture.template.vmm.imp.user.operations;

import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.mapper.UserMapper;
import architecture.template.vmm.user.UserDao;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SearchUserService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserDao userDao;
  private final UserMapper userMapper;
  //endregion

  //region Ctor
  public SearchUserService(
    final UserDao userDao,
    final UserMapper userMapper
  ) {
    this.userDao = userDao;
    this.userMapper = userMapper;
  }
  //endregion

  //region Getters
  public UserEntity handleGetByUuid(UUID uuid) {
    log.info("Finding by uuid: {}", uuid);
    return userDao.getByUuid(uuid);
  }

  public UserDto handleGetByEmailOrUsernameOrCellphone(String userId) {
    log.info("Searching by: {}", userId);
    return userDao.getByEmailOrUsernameOrCellphone(userId);
  }

  public List<UserDto> handleGetAll() {
    return userDao.selectAll()
      .stream()
      .map(userMapper::build)
      .toList();
  }
  //endregion

}
