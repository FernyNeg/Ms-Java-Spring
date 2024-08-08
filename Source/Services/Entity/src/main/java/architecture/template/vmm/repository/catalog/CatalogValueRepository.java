package architecture.template.vmm.repository.catalog;

import architecture.template.vmm.entity.catalog.CatalogValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogValueRepository extends JpaRepository<CatalogValueEntity, Long> {
  List<CatalogValueEntity> findByCatalogName(String name);
}
