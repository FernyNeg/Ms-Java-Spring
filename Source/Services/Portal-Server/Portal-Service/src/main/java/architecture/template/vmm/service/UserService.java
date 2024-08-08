package architecture.template.vmm.service;

import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
  UserDto findByEmailOrUsernameOrCellphone(String userId);

  UserEntity findByUuid(UUID uuid);

  List<UserDto> findAll();

  void update(UserDto dto);

  void delete(UUID userId);

  UUID create(UserDto dto);
}
