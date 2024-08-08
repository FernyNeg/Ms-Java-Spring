package architecture.template.vmm.notification;

import architecture.template.vmm.entity.notification.NotificationEntity;
import architecture.template.vmm.repository.notification.NotificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDao {

  //region Dependencies
  private final NotificationRepository notificationRepository;
  //endregion

  //region Ctor
  public NotificationDao(
    NotificationRepository notificationRepository
  ) {
    this.notificationRepository = notificationRepository;
  }
  //endregion

  //region Getters
  //endregion

  //region Setters
  public NotificationEntity create(NotificationEntity entity) {
    return notificationRepository.save(entity);
  }
  //endregion

}
