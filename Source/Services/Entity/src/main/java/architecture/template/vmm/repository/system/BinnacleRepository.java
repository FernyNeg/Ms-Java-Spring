package architecture.template.vmm.repository.system;

import architecture.template.vmm.entity.system.BinnacleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinnacleRepository extends JpaRepository<BinnacleEntity, Long> {
}
