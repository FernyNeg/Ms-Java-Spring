package architecture.template.vmm.exceptions;

public class DataAlreadyExistException extends RuntimeException {

  public DataAlreadyExistException(String message) {
    super(message);
  }

  public DataAlreadyExistException() {
    super();
  }
}
