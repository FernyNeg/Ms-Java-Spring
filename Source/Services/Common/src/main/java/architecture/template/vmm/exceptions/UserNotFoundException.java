package architecture.template.vmm.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String msg) {
    super(msg);
  }

  public UserNotFoundException() {
    super();
  }
}
