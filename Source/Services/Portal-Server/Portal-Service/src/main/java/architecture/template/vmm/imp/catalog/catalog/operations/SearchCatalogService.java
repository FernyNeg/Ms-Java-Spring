package architecture.template.vmm.imp.catalog.catalog.operations;

import architecture.template.vmm.catalog.CatalogDao;
import architecture.template.vmm.dto.catalog.CatalogDto;
import architecture.template.vmm.mapper.CatalogMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SearchCatalogService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final CatalogDao catalogDao;
  private final CatalogMapper catalogMapper;
  //endregion

  //region Ctor
  public SearchCatalogService(
    CatalogMapper catalogMapper,
    CatalogDao catalogDao
  ) {
    this.catalogMapper = catalogMapper;
    this.catalogDao = catalogDao;
  }
  //endregion

  //region Getters
  public Boolean existByName(String name) {
    return catalogDao.existByName(name);
  }

  public CatalogDto getCatalogByName(String name) {
    log.info("Getting by name: {}", name);
    return catalogMapper.build(catalogDao.getCatalogByName(name));
  }
  //endregion


}
