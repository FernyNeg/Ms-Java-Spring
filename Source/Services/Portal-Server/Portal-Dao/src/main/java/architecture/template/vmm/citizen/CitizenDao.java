package architecture.template.vmm.citizen;

import architecture.template.vmm.entity.citizen.CitizenEntity;
import architecture.template.vmm.repository.citizen.CitizenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CitizenDao {

  //region Dependencies
  private final CitizenRepository citizenRepository;
  //endregion

  //region Ctor
  public CitizenDao(
    CitizenRepository citizenRepository
  ) {
    this.citizenRepository = citizenRepository;
  }
  //endregion

  //region Setters
  public CitizenEntity saveNewCitizen(CitizenEntity citizen) {
    return citizenRepository.save(citizen);
  }
  //endregion

}
