package architecture.template.vmm.imp.system;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import architecture.template.vmm.dto.user.UserDto;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserInSession {

  //region Variables
  private UserDto usuario;
  //endregion

  //region Getters and Setters
  public UserDto getUsuario() {
    return usuario;
  }

  public void setUsuario(UserDto usuario) {
    this.usuario = usuario;
  }
  //endregion
}
