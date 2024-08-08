package architecture.template.vmm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import architecture.template.vmm.dto.notification.NotificationDto;
import architecture.template.vmm.service.NotificationService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/Notification")
@Tag(
  name = "Notifications",
  description = ""
)
public class NotificationController {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final NotificationService notificationService;
  //endregion

  //region Ctor
  public NotificationController(
    NotificationService notificationService
  ) {
    this.notificationService = notificationService;
  }
  //endregion

  //region Controllers
  @Operation(
    description = "",
    summary = ""
  )
  @PostMapping(path = "/Update")
  public UUID updateNotificationsToUser(@RequestBody NotificationDto notificationDto) {
    log.info("Update notification called");
    return notificationService.updateNotification(notificationDto);
  }
  //endregion

}
