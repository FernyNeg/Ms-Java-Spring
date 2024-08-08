package architecture.template.vmm.imp.citizen.citizen.operations;

import architecture.template.vmm.citizen.CitizenDao;
import architecture.template.vmm.entity.citizen.CitizenEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SaveCitizenService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final CitizenDao citizenDao;
  //endregion

  //region Ctor
  public SaveCitizenService(
    CitizenDao citizenDao
  ) {
    this.citizenDao = citizenDao;
  }
  //endregion

  //region Service
  public CitizenEntity handleSave(CitizenEntity citizenEntity) {
    log.info("Saving new citizen");
    return citizenDao.saveNewCitizen(citizenEntity);
  }
  //endregion

}
