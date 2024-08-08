package architecture.template.vmm.repository.notification;

import architecture.template.vmm.entity.notification.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

  Optional<NotificationEntity> findByUuid(UUID uuid);

}
