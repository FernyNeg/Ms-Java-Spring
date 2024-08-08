package architecture.template.vmm.system;

import architecture.template.vmm.entity.system.BinnacleEntity;
import architecture.template.vmm.repository.system.BinnacleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BinnacleDao {

  //region Dependencies
  private final BinnacleRepository binnacleRepository;
  //endregion

  //region Ctor
  public BinnacleDao(BinnacleRepository binnacleRepository) {
    this.binnacleRepository = binnacleRepository;
  }
  //endregion

  //region Setters
  public void setNewLog(BinnacleEntity bin) {
    binnacleRepository.save(bin);
  }
  //endregion

}
