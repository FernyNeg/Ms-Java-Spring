package architecture.template.vmm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import architecture.template.vmm.dto.catalog.CatalogDto;
import architecture.template.vmm.service.CatalogService;

@RestController
@RequestMapping(path = "/Catalog")
@Tag(
  name = "Catalog",
  description = ""
)
public class CatalogController {

  //region Dependencies
  private final CatalogService catalogService;
  //endregion

  //region Ctor
  public CatalogController(
    CatalogService catalogService
  ) {
    this.catalogService = catalogService;
  }
  //endregion

  //region Controller
  @Operation(
    description = "",
    summary = ""
  )
  @PostMapping(path = "/Save")
  public ResponseEntity<CatalogDto> saveNewCatalogList(@RequestBody CatalogDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.saveCatalog(dto));
  }

  @Operation(
    description = "",
    summary = ""
  )
  @GetMapping(path = "/GetByName/{name}")
  public ResponseEntity<CatalogDto> getByName(@PathVariable("name") String name){
    return ResponseEntity.ok(catalogService.getCatalogByName(name));
  }
  //endregion

}
