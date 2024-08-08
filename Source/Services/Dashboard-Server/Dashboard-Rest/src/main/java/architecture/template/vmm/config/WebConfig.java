package architecture.template.vmm.config;

import architecture.template.vmm.service.system.BinnacleService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  //region Dependencies
  private final BinnacleService binnacleService;
  //endregion

  //region Ctor
  public WebConfig(
    BinnacleService binnacleService
  ) {
    this.binnacleService = binnacleService;
  }
  //endregion

  @Bean
  public RequestInterceptor getRequestInterceptor() {
    return new RequestInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(getRequestInterceptor());
  }

  @Bean
  public RequestLoggingFilter getRequestLoggingFilter() {
    return new RequestLoggingFilter(binnacleService);
  }

  @Bean
  public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
    FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(getRequestLoggingFilter());
    registrationBean.addUrlPatterns("/*"); // Configura las URL a las que se aplicará el filtro
    return registrationBean;
  }

}
