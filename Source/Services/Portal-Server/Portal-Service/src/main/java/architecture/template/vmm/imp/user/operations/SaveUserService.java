package architecture.template.vmm.imp.user.operations;

import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.entity.citizen.CatUserCitizenEntity;
import architecture.template.vmm.entity.notification.CatUserNotificationEntity;
import architecture.template.vmm.imp.citizen.catusercitizen.operations.SaveCatUserCitizenService;
import architecture.template.vmm.imp.notification.catusernotification.operations.SaveCatUserNotificationService;
import architecture.template.vmm.mapper.CitizenMapper;
import architecture.template.vmm.mapper.NotificationMapper;
import architecture.template.vmm.user.UserDao;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
@Transactional
public class SaveUserService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CitizenMapper citizenMapper;
  private final NotificationMapper notificationMapper;
  private final UserDao userDao;
  private final SaveCatUserCitizenService saveCatUserCitizenService;
  private final SaveCatUserNotificationService saveCatUserNotificationService;
  //endregion

  //region Ctor
  public SaveUserService(
    final BCryptPasswordEncoder bCryptPasswordEncoder,
    final CitizenMapper citizenMapper,
    final NotificationMapper notificationMapper,
    final UserDao userDao,
    final SaveCatUserCitizenService saveCatUserCitizenService,
    final SaveCatUserNotificationService saveCatUserNotificationService
  ) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.citizenMapper = citizenMapper;
    this.notificationMapper = notificationMapper;
    this.userDao = userDao;
    this.saveCatUserCitizenService = saveCatUserCitizenService;
    this.saveCatUserNotificationService = saveCatUserNotificationService;
  }
  //endregion

  //region Services
  public UserEntity handleNewUser(UserDto user) {
    var userToSave = new UserEntity
      .EntityBuilder()
      .email(user.getEmail())
      .username(user.getUsername())
      .cellphone(user.getCellphone())
      .password(bCryptPasswordEncoder.encode(user.getPassword()))
      .build();
    log.info("Generating new instance");
    userToSave = userDao.insertUser(userToSave);
    log.info("Saved user {}", userToSave.getUuid());
    log.info("Setting Citizen");
    userToSave.setCitizen(
      saveCatUserCitizenService.addCatUserCitizenToSet(
        new HashSet<>(
          Collections.singleton(
            new CatUserCitizenEntity
              .EntityBuilder()
              .citizen(citizenMapper.build(user.getCitizen()))
              .build()
          )
        ),
        userToSave
      )
    );
    log.info("Setting notification");
    userToSave.setNotifications(
      saveCatUserNotificationService.handleCreate(
        new HashSet<>(
          Collections.singleton(
            new CatUserNotificationEntity
              .EntityBuilder()
              .config(notificationMapper.build(user.getNotification()))
              .build()
          )
        ),
        userToSave
      )
    );
    userDao.updateUser(userToSave);
    return userToSave;
  }
  //endregion

}
