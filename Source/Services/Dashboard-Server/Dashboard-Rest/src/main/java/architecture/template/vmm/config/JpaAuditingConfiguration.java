package architecture.template.vmm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

  @Bean
  public AuditorAware<String> auditorProvider() {
//    return () -> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    return () -> Optional.of("desarrollo");
  }
}
