package architecture.template.vmm.citizen;

import architecture.template.vmm.entity.citizen.CatUserCitizenEntity;
import architecture.template.vmm.repository.citizen.CatUserCitizenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CatUserCitizenDao {

  //region Dependencies
  private final CatUserCitizenRepository citizenRepository;
  //endregion

  //region Ctor
  public CatUserCitizenDao(
    CatUserCitizenRepository citizenRepository
  ) {
    this.citizenRepository = citizenRepository;
  }
  //endregion

  //region Getters
  //endregion

  //region Setters
  public CatUserCitizenEntity saveCatUserCitizen(CatUserCitizenEntity citizenEntity) {
    return citizenRepository.save(citizenEntity);
  }
  //endregion

}
