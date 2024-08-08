package architecture.template.vmm.imp.catalog.catalogvalue.operations;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import architecture.template.vmm.catalog.CatalogValueDao;
import architecture.template.vmm.entity.catalog.CatalogEntity;
import architecture.template.vmm.entity.catalog.CatalogValueEntity;

import java.util.Set;

@Service
@Transactional
public class SaveCatalogValueService {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final CatalogValueDao catalogValueDao;
  //endregion

  //region Ctor
  public SaveCatalogValueService(
    CatalogValueDao catalogValueDao
  ) {
    this.catalogValueDao = catalogValueDao;
  }
  //endregion

  //region Setter
  public Set<CatalogValueEntity> saveSetValues(Set<CatalogValueEntity> catalogValue, CatalogEntity catalogParent) {
    for (CatalogValueEntity entity : catalogValue) {
      save(entity, catalogParent);
    }
    return catalogValue;
  }
  //endregion

  //region Functions
  private void save(CatalogValueEntity enti, CatalogEntity catParent) {
    enti.setCatalog(catParent);
    enti = catalogValueDao.saveCatalogValue(enti);
    log.info("Value of {} has new property {}", catParent.getName(), enti.getValue());
  }
  //endregion

}
