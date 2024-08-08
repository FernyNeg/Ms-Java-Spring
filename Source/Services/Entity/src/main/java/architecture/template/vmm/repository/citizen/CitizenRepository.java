package architecture.template.vmm.repository.citizen;

import architecture.template.vmm.entity.citizen.CitizenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenEntity, Long> {
}
