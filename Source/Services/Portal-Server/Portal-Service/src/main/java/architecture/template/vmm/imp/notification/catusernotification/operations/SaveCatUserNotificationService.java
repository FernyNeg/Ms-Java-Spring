package architecture.template.vmm.imp.notification.catusernotification.operations;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import architecture.template.vmm.dto.notification.NotificationDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.entity.notification.CatUserNotificationEntity;
import architecture.template.vmm.entity.notification.NotificationEntity;
import architecture.template.vmm.imp.notification.notification.operations.SaveNotificationService;
import architecture.template.vmm.notification.CatUserNotificationDao;
import architecture.template.vmm.user.UserDao;

import java.util.Set;
import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_DISABLE;

@Service
@Transactional
public class SaveCatUserNotificationService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserDao userDao;
  private final SaveNotificationService saveNotificationService;
  private final CatUserNotificationDao catUserNotificationDao;
  //endregion

  //region Ctor
  public SaveCatUserNotificationService(
    final UserDao userDao,
    final CatUserNotificationDao catUserNotificationDao,
    final SaveNotificationService saveNotificationService
  ) {
    this.userDao = userDao;
    this.saveNotificationService = saveNotificationService;
    this.catUserNotificationDao = catUserNotificationDao;
  }
  //endregion

  //region Service
  public Set<CatUserNotificationEntity> handleCreate(Set<CatUserNotificationEntity> entityList, UserEntity userParent) {
    log.info("handleCreate");
    if (!entityList.isEmpty())
      for (CatUserNotificationEntity entity : entityList)
        saveCatUserNotification(entity, userParent);
    return entityList;
  }

  public UUID handleUpdate(NotificationDto dto) {
    var userToUpdate = userDao.getByNotificationUuid(dto.getUuid());
    log.info("Disabling latest configs");
    for (CatUserNotificationEntity noti : userToUpdate.getNotifications()) {
      log.info("Disabling history: {}", noti.getUuid());
      noti.setFdl(DEFAULT_FDL_DISABLE);
      noti.getConfig().setFdl(DEFAULT_FDL_DISABLE);
      saveCatUserNotification(noti, userToUpdate);
    }
    var ret = new CatUserNotificationEntity.EntityBuilder()
      .config(new NotificationEntity
        .EntityBuilder()
        .email(dto.getEmail())
        .phone(dto.getPhone())
        .push(dto.getPush())
        .whatsapp(dto.getWhatsapp())
        .build()
      )
      .build();
    saveCatUserNotification(ret, userToUpdate);
    return ret.getUuid();
  }
  //endregion

  //region Functions
  private void saveCatUserNotification(CatUserNotificationEntity entity, UserEntity userParent) {
    log.info("Saving new Notification config");
    entity.setUser(userParent);
    entity = catUserNotificationDao.create(entity);
    log.info("Setting config at: {}", entity.getUuid());
    entity.setConfig(
      saveNotificationService.handleCreate(entity.getConfig(), entity)
    );
    entity = catUserNotificationDao.create(entity);
    log.info("Saved config as: {}", entity.getUuid());
  }

  //endregion

}
