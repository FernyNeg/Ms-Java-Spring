package architecture.template.vmm.mapper;

import architecture.template.vmm.dto.notification.NotificationDto;
import architecture.template.vmm.entity.notification.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

  public NotificationEntity build(NotificationDto notificationDto) {
    return new NotificationEntity
      .EntityBuilder()
      .uuid(notificationDto.getUuid())
      .email(notificationDto.getEmail())
      .phone(notificationDto.getPhone())
      .whatsapp(notificationDto.getWhatsapp())
      .push(notificationDto.getPush())
      .build();
  }

  public NotificationDto build(NotificationEntity notificationEntity) {
    return new NotificationDto
      .EntityBuilder()
      .setUuid(notificationEntity.getUuid())
      .setEmail(notificationEntity.getEmail())
      .setPhone(notificationEntity.getPhone())
      .setWhatsapp(notificationEntity.getWhatsapp())
      .setPush(notificationEntity.getPush())
      .build();
  }

}
