package architecture.template.vmm.repository.citizen;

import architecture.template.vmm.entity.citizen.CatUserCitizenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatUserCitizenRepository extends JpaRepository<CatUserCitizenEntity, Long> {
}
