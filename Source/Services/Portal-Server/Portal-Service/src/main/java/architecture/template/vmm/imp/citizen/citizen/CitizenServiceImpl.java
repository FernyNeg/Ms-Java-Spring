package architecture.template.vmm.imp.citizen.citizen;

import architecture.template.vmm.service.CitizenService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import architecture.template.vmm.dto.citizen.CitizenDto;
import architecture.template.vmm.imp.citizen.catusercitizen.operations.SaveCatUserCitizenService;

import java.util.UUID;

@Service
@Transactional
public class CitizenServiceImpl implements CitizenService {

  //region Dependencies
  private final SaveCatUserCitizenService saveCatUserCitizenService;
  //endregion

  //region Ctor
  public CitizenServiceImpl(
    final SaveCatUserCitizenService saveCatUserCitizenService
  ) {
    this.saveCatUserCitizenService = saveCatUserCitizenService;
  }
  //endregion

  @Override
  public UUID updateCitizenToUser(CitizenDto citizen) {
    return saveCatUserCitizenService.updateCatUserCitizenToSet(citizen);
  }
}
