package architecture.template.vmm.model.request;

import jakarta.validation.constraints.NotNull;

public class AuthRequest {

  //region Variables
  @NotNull
  private String username;
  @NotNull
  private String password;
  //endregion

  //region Ctor
  public AuthRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public AuthRequest() {
  }
  //endregion

  //region Getters and Setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  //endregion

}
