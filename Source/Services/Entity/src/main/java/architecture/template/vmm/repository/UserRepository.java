package architecture.template.vmm.repository;

import architecture.template.vmm.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByEmailOrUsernameOrCellphoneAndFdlTrue(String email, String username, String cellphoneString);

  Optional<UserEntity> findByEmailAndFdlOrUsernameAndFdlOrCellphoneAndFdl(String email, Boolean fdl, String username, Boolean fdl2, String cellphone, Boolean fdl3);

  List<UserEntity> findByFdl(Boolean fdl);

  Optional<UserEntity> findByUuidAndFdl(UUID uuid, Boolean fdl);

  Optional<UserEntity> findByUsernameAndPasswordAndFdl(String username, String password, Boolean fdl);

  Optional<UserEntity> findByCitizenCitizenUuid(UUID uuid);

  Optional<UserEntity> findByNotificationsConfigUuid(UUID uuid);
}
