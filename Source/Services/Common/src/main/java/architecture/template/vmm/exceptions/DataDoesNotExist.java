package architecture.template.vmm.exceptions;

public class DataDoesNotExist extends RuntimeException {
  public DataDoesNotExist(String message) {
    super(message);
  }

  public DataDoesNotExist(String message, Throwable cause) {
    super(message, cause);
  }
}
