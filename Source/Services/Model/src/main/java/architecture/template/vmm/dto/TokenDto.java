package architecture.template.vmm.dto;

public class TokenDto {

  //region Variables
  private String accessToken;
  //endregion

  //region Ctor
  public TokenDto(String accessToken) {
    this.accessToken = accessToken;
  }
  public TokenDto() {
  }
  //endregion

  //region Getters and Setters
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  //endregion

}
