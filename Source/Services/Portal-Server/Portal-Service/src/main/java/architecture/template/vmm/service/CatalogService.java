package architecture.template.vmm.service;

import architecture.template.vmm.dto.catalog.CatalogDto;

public interface CatalogService {

  CatalogDto getCatalogByName(String catalogName);

  CatalogDto saveCatalog(CatalogDto catalog);

}
