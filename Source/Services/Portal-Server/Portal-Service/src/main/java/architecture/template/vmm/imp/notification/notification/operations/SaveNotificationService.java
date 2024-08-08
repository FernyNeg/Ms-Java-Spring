package architecture.template.vmm.imp.notification.notification.operations;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import architecture.template.vmm.entity.notification.CatUserNotificationEntity;
import architecture.template.vmm.entity.notification.NotificationEntity;
import architecture.template.vmm.notification.NotificationDao;

@Service
@Transactional
public class SaveNotificationService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final NotificationDao notificationDao;
  //endregion

  //region Ctor
  public SaveNotificationService(
    NotificationDao notificationDao
  ) {
    this.notificationDao = notificationDao;
  }
  //endregion

  //region Service
  public NotificationEntity handleCreate(NotificationEntity entity, CatUserNotificationEntity catParent) {
    log.info("Creating new Notification entity  ");
    entity.setCatalog(catParent);
    return notificationDao.create(entity);
  }
  //endregion

}
