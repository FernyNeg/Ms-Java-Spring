package architecture.template.vmm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import architecture.template.vmm.dto.user.UserDto;
import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.service.UserService;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(path = "/User")
@Tag(name = "User", description = "")
public class UserController {

  //region Dependencies
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserService userService;
  //endregion

  //region Ctor
  public UserController(
    final UserService userService
  ) {
    this.userService = userService;
  }
  //endregion

  //region Controllers
  @Operation(description = "", summary = "")
  @PostMapping(path = "/SaveNewUser")
  public ResponseEntity<String> saveNewUser(@Valid @RequestBody UserDto dto, UriComponentsBuilder uriComponentsBuilder) {
    log.info("Save user called");
    var userId = userService.create(dto);
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(userId.toString());
  }

  @Operation(
    description = "",
    summary = ""
  )
  @GetMapping(path = "/{userId}")
  public ResponseEntity<UserDto> find(@PathVariable("userId") String userId) {
    log.info("find called");
    UserDto body = userService.findByEmailOrUsernameOrCellphone(userId);
    return ResponseEntity.ok(body);
  }

  @Operation(
    description = "",
    summary = ""
  )
  @GetMapping(path = "/Uuid/{userId}")
  public ResponseEntity<UserEntity> findByUuid(@PathVariable("userId") UUID userId) {
    log.info("findByUuid called");
    UserEntity body = userService.findByUuid(userId);
    return ResponseEntity.ok(body);
  }

  @Operation(
    description = "",
    summary = ""
  )
  @GetMapping
  public ResponseEntity<List<UserDto>> findAll() {
    List<UserDto> body = userService.findAll();
    return body.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(body);
  }

  @Operation(
    description = "",
    summary = ""
  )
  @PutMapping()
  public ResponseEntity<Void> update(@Valid @RequestBody UserDto dto) {
    userService.update(dto);
    return ResponseEntity.ok().build();
  }

  @Operation(
    description = "",
    summary = ""
  )
  @DeleteMapping(path = "/{userId}")
  public ResponseEntity<Void> delete(@Valid @PathVariable("userId") UUID userId) {
    log.info("Delete called");
    userService.delete(userId);
    return ResponseEntity.ok().build();
  }
  //endregion

}
