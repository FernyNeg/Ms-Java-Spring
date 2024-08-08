package architecture.template.vmm.imp.user;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.imp.user.operations.DeleteUserService;
import architecture.template.vmm.imp.user.operations.SaveUserService;
import architecture.template.vmm.imp.user.operations.SearchUserService;
import architecture.template.vmm.imp.user.operations.UpdateUserService;
import architecture.template.vmm.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  //region Dependencies
  private final SearchUserService searchUserService;
  private final UpdateUserService updateUserService;
  private final DeleteUserService deleteUserService;
  private final SaveUserService saveUserService;
  //endregion

  //region Ctor
  public UserServiceImpl(
    final SearchUserService searchUserService,
    final UpdateUserService updateUserService,
    final DeleteUserService deleteUserService,
    final SaveUserService saveUserService
  ) {
    this.searchUserService = searchUserService;
    this.updateUserService = updateUserService;
    this.deleteUserService = deleteUserService;
    this.saveUserService = saveUserService;
  }
  //endregion

  //region Services
  @Override
  public UserDto findByEmailOrUsernameOrCellphone(String userId) {
    return searchUserService.handleGetByEmailOrUsernameOrCellphone(userId);
  }

  @Override
  public UserEntity findByUuid(UUID uuid) {
    return searchUserService.handleGetByUuid(uuid);
  }

  @Override
  public List<UserDto> findAll() {
    return searchUserService.handleGetAll();
  }

  @Override
  public void update(UserDto dto) {
    updateUserService.handleUpdate(dto);
  }

  @Override
  public void delete(@Valid UUID userId) {
    deleteUserService.handleDelete(userId);
  }

  @Override
  public UUID create(UserDto dto) {
    return saveUserService.handleNewUser(dto).getUuid();
  }
  //endregion
}
