package architecture.template.vmm.imp.notification.notification;

import architecture.template.vmm.service.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import architecture.template.vmm.dto.notification.NotificationDto;
import architecture.template.vmm.imp.notification.catusernotification.operations.SaveCatUserNotificationService;

import java.util.UUID;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

  //region Dependencies
  private final SaveCatUserNotificationService saveCatUserNotificationService;
  //endregion

  //region Ctor
  public NotificationServiceImpl(
    SaveCatUserNotificationService saveCatUserNotificationService
  ) {
    this.saveCatUserNotificationService = saveCatUserNotificationService;
  }
  //endregion

  @Override
  public UUID updateNotification(NotificationDto notification) {
    return saveCatUserNotificationService.handleUpdate(notification);
  }
}
