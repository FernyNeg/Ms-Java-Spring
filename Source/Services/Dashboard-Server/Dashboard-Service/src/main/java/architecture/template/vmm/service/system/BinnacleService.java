package architecture.template.vmm.service.system;

import architecture.template.vmm.system.BinnacleDao;
import architecture.template.vmm.entity.system.BinnacleEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BinnacleService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final BinnacleDao binnacleDao;
  //endregion

  //region Ctor
  public BinnacleService(
    final BinnacleDao binnacleDao
  ) {
    this.binnacleDao = binnacleDao;
  }
  //endregion

  //region Services
  public void createBinnacle(BinnacleEntity binnacleEntity) {
    log.info("Adding new log registry");
    binnacleDao.setNewLog(binnacleEntity);
  }
  //endregion

}
