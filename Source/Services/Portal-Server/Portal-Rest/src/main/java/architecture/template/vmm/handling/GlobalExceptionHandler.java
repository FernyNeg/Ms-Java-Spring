package architecture.template.vmm.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import architecture.template.vmm.constants.ConfigConstants;
import architecture.template.vmm.exceptions.DataAlreadyExistException;
import architecture.template.vmm.exceptions.DataDoesNotExist;
import architecture.template.vmm.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .header(ConfigConstants.HEADER_ERROR_MESSAGE, ex.getMessage())
      .body(ex.getMessage());
  }

  @ExceptionHandler(DataAlreadyExistException.class)
  public ResponseEntity<String> handleDataAlreadyExistException(DataAlreadyExistException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
      .header(ConfigConstants.HEADER_ERROR_MESSAGE, ex.getMessage())
      .body(ex.getMessage());
  }

  @ExceptionHandler(DataDoesNotExist.class)
  public ResponseEntity<String> handleDataAlreadyExistException(DataDoesNotExist ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .header(ConfigConstants.HEADER_ERROR_MESSAGE, ex.getMessage())
      .body(ex.getMessage());
  }

}
