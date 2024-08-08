package architecture.template.vmm.config;

import architecture.template.vmm.functions.GetIpFromHeaders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import architecture.template.vmm.entity.system.BinnacleEntity;
import architecture.template.vmm.imp.system.BinnacleService;

import java.io.IOException;

public class RequestLoggingFilter extends OncePerRequestFilter {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final BinnacleService binnacleService;
  //endregion

  //region Ctor
  public RequestLoggingFilter(
    BinnacleService binnacleService
  ) {
    this.binnacleService = binnacleService;
  }
  //endregion

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    log.info("Do filter Internal");
    addLogToDb(request);
    filterChain.doFilter(request, response);
  }

  //region Functions
  private void addLogToDb(HttpServletRequest request) {
    var bin = new BinnacleEntity
      .EntityBuilder()
      .ipaddress(GetIpFromHeaders.getClientIpAddress(request))
      .rute(request.getRequestURI())
      .method(request.getMethod())
      .build();
    binnacleService.createBinnacle(bin);
  }
  //endregion

}