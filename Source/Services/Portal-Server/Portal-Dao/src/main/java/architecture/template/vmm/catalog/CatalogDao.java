package architecture.template.vmm.catalog;

import architecture.template.vmm.entity.catalog.CatalogEntity;
import architecture.template.vmm.exceptions.DataAlreadyExistException;
import architecture.template.vmm.exceptions.DataDoesNotExist;
import architecture.template.vmm.repository.catalog.CatalogRepository;
import org.springframework.stereotype.Repository;

import static architecture.template.vmm.messages.ExceptionsConstants.CATALOG_NOT_FOUND;

@Repository
public class CatalogDao {

  //region Dependencies
  private final CatalogRepository catalogRepository;
  //endregion

  //region Ctor
  public CatalogDao(
    CatalogRepository catalogRepository
  ) {
    this.catalogRepository = catalogRepository;
  }
  //endregion

  //region Setters
  public CatalogEntity insertCatalog(CatalogEntity catalog) {
    validateIfNotExist(catalog);
    return catalogRepository.save(catalog);
  }

  public CatalogEntity saveCatalog(CatalogEntity catalog) {
    return catalogRepository.save(catalog);
  }
  //endregion

  //region Getters
  public Boolean existByName(String name) {
    return catalogRepository.existsByName(name);
  }

  public CatalogEntity getCatalogByName(String name) {
    return catalogRepository.findByName(name).orElseThrow(()->new DataDoesNotExist(CATALOG_NOT_FOUND));
  }
  //endregion

  //region Functions
  private void validateIfNotExist(CatalogEntity catalog) {
    var savedCatalog = this.existByName(catalog.getName());
    if (Boolean.TRUE.equals(savedCatalog)) {
      throw new DataAlreadyExistException("Catalog with name " + catalog.getName() + " already exists");
    }
  }
  //endregion

}
