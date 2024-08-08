package architecture.template.vmm.repository.catalog;

import architecture.template.vmm.entity.catalog.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogEntity, Long> {

  Boolean existsByName(String name);

  Optional<CatalogEntity> findByName(String name);

}
