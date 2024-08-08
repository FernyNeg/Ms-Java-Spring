package architecture.template.vmm.catalog;

import architecture.template.vmm.entity.catalog.CatalogValueEntity;
import architecture.template.vmm.repository.catalog.CatalogValueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatalogValueDao {

  //region Dependencies
  private final CatalogValueRepository catalogValueRepository;
  //endregion

  //region Ctor
  public CatalogValueDao(
    CatalogValueRepository catalogValueRepository
  ) {
    this.catalogValueRepository = catalogValueRepository;
  }
  //endregion

  //region Getters
  public List<CatalogValueEntity> getByParentId(String name) {
    return catalogValueRepository.findByCatalogName(name);
  }
  //endregion

  //region Setters
  public CatalogValueEntity saveCatalogValue(CatalogValueEntity entity) {
    return catalogValueRepository.save(entity);
  }
  //endregion

}
