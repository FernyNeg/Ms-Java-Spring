package architecture.template.vmm.mapper;

import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.entity.citizen.CatUserCitizenEntity;
import architecture.template.vmm.entity.citizen.CitizenEntity;
import architecture.template.vmm.entity.notification.CatUserNotificationEntity;
import architecture.template.vmm.entity.notification.NotificationEntity;
import architecture.template.vmm.entity.system.Auditable;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  //region Dependencies
  private final CitizenMapper citizenMapper;
  private final NotificationMapper notificationMapper;
  //endregion

  //region Ctor
  public UserMapper(
    CitizenMapper citizenMapper,
    NotificationMapper notificationMapper
  ) {
    this.citizenMapper = citizenMapper;
    this.notificationMapper = notificationMapper;
  }
  //endregion

  public UserDto build(UserEntity ent) {
    return new UserDto
      .EntityBuilder(ent.getUsername(), ent.getEmail(), ent.getCellphone(), ent.getPassword())
      .uuid(ent.getUuid())

      .citizen(citizenMapper.build(
        ent.getCitizen()
          .stream()
          .filter(Auditable::getFdl)
          .map(CatUserCitizenEntity::getCitizen)
          .findFirst()
          .orElse(new CitizenEntity())))

      .notification(notificationMapper.build(
        ent.getNotifications()
          .stream()
          .filter(Auditable::getFdl)
          .map(CatUserNotificationEntity::getConfig)
          .findFirst()
          .orElse(new NotificationEntity())))

      .build();
  }

  public UserEntity build(UserDto dto) {
    return new UserEntity
      .EntityBuilder()
      .uuid(dto.getUuid())
      .username(dto.getUsername())
      .cellphone(dto.getCellphone())
      .email(dto.getEmail())
      .password(dto.getPassword())
      .build();
  }

}
