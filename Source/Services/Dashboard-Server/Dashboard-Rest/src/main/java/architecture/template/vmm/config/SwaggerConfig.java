package architecture.template.vmm.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "Dashboard server",
    description = "Servicios dedicados al dashboard de nuestro sistema",
    version = "1.0"
  )
)
public class SwaggerConfig {
}
