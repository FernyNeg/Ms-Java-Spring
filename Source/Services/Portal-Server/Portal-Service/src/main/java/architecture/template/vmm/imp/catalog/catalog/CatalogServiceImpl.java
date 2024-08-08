package architecture.template.vmm.imp.catalog.catalog;

import architecture.template.vmm.dto.catalog.CatalogDto;
import architecture.template.vmm.imp.catalog.catalog.operations.SaveCatalogService;
import architecture.template.vmm.imp.catalog.catalog.operations.SearchCatalogService;
import architecture.template.vmm.service.CatalogService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

  //region Dependencies
  private final SearchCatalogService searchCatalogService;
  private final SaveCatalogService saveCatalogService;
  //endregion

  //region Ctor
  public CatalogServiceImpl(
    SearchCatalogService searchCatalogService,
    SaveCatalogService saveCatalogService
  ) {
    this.searchCatalogService = searchCatalogService;
    this.saveCatalogService = saveCatalogService;
  }
  //endregion

  @Override
  public CatalogDto getCatalogByName(String catalogName) {
    return searchCatalogService.getCatalogByName(catalogName);
  }

  @Override
  public CatalogDto saveCatalog(CatalogDto catalog) {
    return saveCatalogService.handleSave(catalog);
  }

}
