package architecture.template.vmm.imp.citizen.catusercitizen.operations;

import architecture.template.vmm.citizen.CatUserCitizenDao;
import architecture.template.vmm.dto.citizen.CitizenDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.entity.citizen.CatUserCitizenEntity;
import architecture.template.vmm.entity.citizen.CitizenEntity;
import architecture.template.vmm.imp.citizen.citizen.operations.SaveCitizenService;
import architecture.template.vmm.user.UserDao;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_DISABLE;

@Service
@Transactional
public class SaveCatUserCitizenService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final CatUserCitizenDao catUserCitizenDao;
  private final UserDao userDao;
  private final SaveCitizenService saveCitizenService;
  //endregion

  //region Ctor
  public SaveCatUserCitizenService(
    final CatUserCitizenDao catUserCitizenDao,
    final UserDao userDao,
    final SaveCitizenService saveCitizenService
  ) {
    this.catUserCitizenDao = catUserCitizenDao;
    this.userDao = userDao;
    this.saveCitizenService = saveCitizenService;
  }
  //endregion

  public Set<CatUserCitizenEntity> addCatUserCitizenToSet(
    Set<CatUserCitizenEntity> citizenList,
    UserEntity userParent
  ) {
    for (CatUserCitizenEntity citizen : citizenList) {
      save(citizen, userParent);
    }
    return citizenList;
  }

  /**
   * @implNote Agregamos el nuevo cambio del ciudadano como un CatUserCitizenEntity
   * @apiNote Actualizamos el historial de los ciudadanos
   */
  public UUID updateCatUserCitizenToSet(CitizenDto catalog) {
    var userToUpdate = userDao.getByCitizenUuid(catalog.getUuid());
    log.info("Disabling latest citizens");
    for (CatUserCitizenEntity item : userToUpdate.getCitizen()) {
      log.info("Disabling history: {}", item.getUuid());
      item.setFdl(DEFAULT_FDL_DISABLE);
      item.getCitizen().setFdl(DEFAULT_FDL_DISABLE);
      save(item, userToUpdate);
    }
    var ret = new CatUserCitizenEntity
      .EntityBuilder()
      .citizen(new CitizenEntity.EntityBuilder()
        .names(catalog.getNames())
        .lastname1(catalog.getLastname1())
        .lastname2(catalog.getLastname2())
        .birthdate(catalog.getBirthdate())
        .curp(catalog.getCurp())
        .rfc(catalog.getRfc())
        .build()
      )
      .build();
    save(ret, userToUpdate);
    return ret.getCitizen().getUuid();
  }

  private void save(
    CatUserCitizenEntity entity,
    UserEntity userParent
  ) {
    log.info("Adding user to Cat");
    entity.setUser(userParent);
    log.info("Adding citizen to Cat");
    entity.setCitizen(
      saveCitizenService.handleSave(entity.getCitizen())
    );
    log.info("Adding new Cat User Citizen");
    entity = catUserCitizenDao.saveCatUserCitizen(entity);
    log.info("Cat User Citizen created as: {}", entity.getUuid());
  }

}
