package architecture.template.vmm.user;

import architecture.template.vmm.constants.SystemConstants;
import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.exceptions.DataAlreadyExistException;
import architecture.template.vmm.exceptions.UserNotFoundException;
import architecture.template.vmm.mapper.UserMapper;

import architecture.template.vmm.model.request.AuthRequest;
import architecture.template.vmm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_ENABLED;
import static architecture.template.vmm.messages.ExceptionsConstants.*;

@Repository
public class UserDao {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  //endregion

  //region Ctor
  public UserDao(
    final UserRepository userRepository,
    final UserMapper userMapper
  ) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }
  //endregion

  //region Getter Queries
  public UserDto getByEmailOrUsernameOrCellphone(String filter) {
    return userRepository.findByEmailOrUsernameOrCellphoneAndFdlTrue(filter, filter, filter)
      .map(userMapper::build)
      .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  public UserEntity getByUuid(UUID uuid) {
    return userRepository.findByUuidAndFdl(uuid, DEFAULT_FDL_ENABLED)
      .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  public List<UserEntity> selectAll() {
    return userRepository.findByFdl(DEFAULT_FDL_ENABLED);
  }

  public UserDto getForSession(AuthRequest authRequest) {
    return userRepository.findByUsernameAndPasswordAndFdl(authRequest.getUsername(), authRequest.getPassword(), DEFAULT_FDL_ENABLED)
      .map(userMapper::build)
      .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  public UserEntity getByCitizenUuid(UUID citizenUuid) {
    return userRepository.findByCitizenCitizenUuid(citizenUuid).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }

  public UserEntity getByNotificationUuid(UUID notificationUuid) {
    return userRepository.findByNotificationsConfigUuid(notificationUuid).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
  }
  //endregion

  //region Update Queries
  public void updateUser(UserEntity userEntity) {
    userEntity = userRepository.save(userEntity);
    log.info("Se actualiza el usuario {}", userEntity.getUuid());
  }

  public UserEntity insertUser(UserEntity userEntity) {
    validateIfNotExist(userEntity);
    userEntity = userRepository.save(userEntity);
    log.info("Se agrega el nuevo usuario {}", userEntity.getUuid());
    return userEntity;
  }
  //endregion

  //region Functions
  private void validateIfNotExist(UserEntity userEntity) {
    Optional<UserEntity> savedUser = userRepository.findByEmailOrUsernameOrCellphoneAndFdlTrue(userEntity.getEmail(), userEntity.getUsername(), userEntity.getCellphone());
    if (savedUser.isPresent()) {
      if (savedUser.get().getEmail().equals(userEntity.getEmail()))
        throw new DataAlreadyExistException(EMAIL_EXIST);
      if (savedUser.get().getUsername().equals(userEntity.getUsername()))
        throw new DataAlreadyExistException(USERNAME_EXIST);
      if (savedUser.get().getCellphone().equals(userEntity.getCellphone()))
        throw new DataAlreadyExistException(CELLPHONE_EXIST);
      throw new DataAlreadyExistException(DATA_ALREADY_EXIST);
    }
  }
  //endregion

}
