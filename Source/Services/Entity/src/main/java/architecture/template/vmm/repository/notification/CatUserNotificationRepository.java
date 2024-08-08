package architecture.template.vmm.repository.notification;

import architecture.template.vmm.entity.notification.CatUserNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatUserNotificationRepository extends JpaRepository<CatUserNotificationEntity, Long> {



}
