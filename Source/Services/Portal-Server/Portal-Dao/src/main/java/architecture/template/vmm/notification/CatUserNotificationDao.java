package architecture.template.vmm.notification;

import architecture.template.vmm.entity.notification.CatUserNotificationEntity;
import architecture.template.vmm.repository.notification.CatUserNotificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CatUserNotificationDao {

  //region Dependencies
  private final CatUserNotificationRepository catUserNotificationRepository;
  //endregion

  //region Ctor
  public CatUserNotificationDao(
    CatUserNotificationRepository catUserNotificationRepository
  ) {
    this.catUserNotificationRepository = catUserNotificationRepository;
  }
  //endregion

  //region Setters
  public CatUserNotificationEntity create(CatUserNotificationEntity entity) {
    return catUserNotificationRepository.save(entity);
  }
  //endregion

}
