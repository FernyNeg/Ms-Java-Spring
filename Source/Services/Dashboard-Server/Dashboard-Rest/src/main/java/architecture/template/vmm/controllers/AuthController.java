package architecture.template.vmm.controllers;

import architecture.template.vmm.dto.TokenDto;
import architecture.template.vmm.model.request.AuthRequest;
import architecture.template.vmm.service.system.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static architecture.template.vmm.constants.HeadersConstants.ACCESS_TOKEN_HEADER_NAME;

@RestController
@RequestMapping(path = "/Auth")
@Tag(name = "Auth", description = "")
public class AuthController {

  //region Dependencies
  private final AuthService authService;
  //endregion

  //region Ctor
  public AuthController(AuthService authService) {
    this.authService = authService;
  }
  //endregion

  //region Controllers
  @PostMapping(path = "login") //password secret
  public ResponseEntity<TokenDto> jwtCreate(@RequestBody AuthRequest user) {
    return ResponseEntity.ok(this.authService.login(user));
  }

  @PostMapping(path = "jwt")
  public ResponseEntity<TokenDto> jwtValidate(@RequestHeader(ACCESS_TOKEN_HEADER_NAME) String accessToken) {
    return ResponseEntity.ok(this.authService.validateToken(new TokenDto(accessToken)));
  }
  //endregion

}
