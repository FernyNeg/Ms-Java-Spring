package architecture.template.vmm.mapper;

import architecture.template.vmm.dto.citizen.CitizenDto;
import architecture.template.vmm.entity.citizen.CitizenEntity;
import org.springframework.stereotype.Component;

@Component
public class CitizenMapper {
  public CitizenDto build(CitizenEntity entity) {
    return new CitizenDto
      .EntityBuilder()
      .uuid(entity.getUuid())
      .names(entity.getNames())
      .lastname1(entity.getLastname1())
      .lastname2(entity.getLastname2())
      .birthdate(entity.getBirthdate())
      .curp(entity.getCurp())
      .rfc(entity.getRfc())
      .build();
  }

  public CitizenEntity build(CitizenDto dto) {
    return new CitizenEntity
      .EntityBuilder()
      .uuid(dto.getUuid())
      .names(dto.getNames())
      .lastname1(dto.getLastname1())
      .lastname2(dto.getLastname2())
      .birthdate(dto.getBirthdate())
      .curp(dto.getCurp())
      .rfc(dto.getRfc())
      .build();
  }
}
