package architecture.template.vmm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import architecture.template.vmm.dto.citizen.CitizenDto;
import architecture.template.vmm.service.CitizenService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/Citizen")
@Tag(
  name = "Citizen",
  description = ""
)
public class CitizenController {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final CitizenService citizenService;
  //endregion

  //region Ctor
  public CitizenController(
    CitizenService citizenService
  ) {
    this.citizenService = citizenService;
  }

  //endregion
  @Operation(
    description = "",
    summary = ""
  )
  @PostMapping(path = "/Update")
  public ResponseEntity<UUID> updateCitizen(@RequestBody CitizenDto citizenDto) {
    log.info("Update called");
    return ResponseEntity
      .status(HttpStatus.ACCEPTED)
      .body(citizenService.updateCitizenToUser(citizenDto));
  }

}
