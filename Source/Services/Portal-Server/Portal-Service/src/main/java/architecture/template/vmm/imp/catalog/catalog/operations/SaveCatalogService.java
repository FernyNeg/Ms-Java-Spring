package architecture.template.vmm.imp.catalog.catalog.operations;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import architecture.template.vmm.catalog.CatalogDao;
import architecture.template.vmm.dto.catalog.CatalogDto;
import architecture.template.vmm.entity.catalog.CatalogEntity;
import architecture.template.vmm.imp.catalog.catalogvalue.operations.SaveCatalogValueService;
import architecture.template.vmm.mapper.CatalogMapper;
import architecture.template.vmm.mapper.CatalogValueMapper;

@Service
@Transactional
public class SaveCatalogService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final CatalogDao catalogDao;
  private final SaveCatalogValueService saveCatalogValueService;
  private final CatalogMapper catalogMapper;
  private final CatalogValueMapper catalogValueMapper;
  //endregion

  //region Ctor
  public SaveCatalogService(
    final CatalogDao catalogDao,
    final SaveCatalogValueService saveCatalogValueService,
    final CatalogMapper catalogMapper,
    final CatalogValueMapper catalogValueMapper
  ) {
    this.catalogDao = catalogDao;
    this.saveCatalogValueService = saveCatalogValueService;
    this.catalogMapper = catalogMapper;
    this.catalogValueMapper = catalogValueMapper;
  }
  //endregion

  //region Service
  public CatalogDto handleSave(CatalogDto catalog) {
    var catalogToSave = new CatalogEntity.EntityBuilder().name(catalog.getName()).build();
    log.info("Generating new instance");
    catalogToSave = catalogDao.insertCatalog(catalogToSave);
    log.info("New catalog was saved as {}", catalogToSave.getUuid());
    catalogToSave.setValues(
      saveCatalogValueService.saveSetValues(
        catalogValueMapper.buildDtoSet(catalog.getValues()),
        catalogToSave
      )
    );
    catalogToSave = catalogDao.saveCatalog(catalogToSave);
    catalog = catalogMapper.build(catalogToSave);
    log.info("Catalog {} has new {} items", catalogToSave.getUuid(), catalogToSave.getValues().size());
    return catalog;
  }
  //endregion

  //region Function
  //endregion

}
