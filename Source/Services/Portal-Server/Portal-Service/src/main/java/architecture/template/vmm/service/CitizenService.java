package architecture.template.vmm.service;

import architecture.template.vmm.dto.citizen.CitizenDto;

import java.util.UUID;

public interface CitizenService {

  UUID updateCitizenToUser(CitizenDto citizen);

}
