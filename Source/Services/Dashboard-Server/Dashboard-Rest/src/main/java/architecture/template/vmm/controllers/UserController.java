package architecture.template.vmm.controllers;

import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/User")
@Tag(name = "User", description = "")
public class UserController {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserService userService;
  //endregion

  //region Ctor
  public UserController(final UserService userService) {
    this.userService = userService;
  }
  //endregion

  @Operation(
    description = "",
    summary = ""
  )
  @PostMapping(path = "/SaveNewUser")
  public ResponseEntity<String> saveNewUser(@RequestBody final UserDto user) {
    log.info("Save new user called");
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveNewUser(user));
  }

}
