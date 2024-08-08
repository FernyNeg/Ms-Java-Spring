package architecture.template.vmm.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
public class RequestInterceptor implements HandlerInterceptor {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  //endregion

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {
    log.info("Pre-Handle");
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) {
    log.info("Post-Handle");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
    log.info("After-Completion");
  }

}
