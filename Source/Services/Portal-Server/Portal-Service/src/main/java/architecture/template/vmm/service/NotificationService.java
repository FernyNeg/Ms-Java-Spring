package architecture.template.vmm.service;

import architecture.template.vmm.dto.notification.NotificationDto;

import java.util.UUID;

public interface NotificationService {
  UUID updateNotification(NotificationDto notification);
}
