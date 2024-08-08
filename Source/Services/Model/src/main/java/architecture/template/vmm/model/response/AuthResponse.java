package architecture.template.vmm.model.response;

import architecture.template.vmm.dto.user.UserDto;

public class AuthResponse {

  //region Variables
  private String token;
  private UserDto user;
  //endregion

  //region Ctor
  public AuthResponse(String token, UserDto user) {
    this.token = token;
    this.user = user;
  }

  public AuthResponse() {
  }
  //endregion

  //region Getters and Setters
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }
  //endregion

}
