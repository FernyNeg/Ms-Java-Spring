package architecture.template.vmm.dto.user;

import architecture.template.vmm.dto.citizen.CitizenDto;
import architecture.template.vmm.dto.notification.NotificationDto;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class UserDto {

  //region Variables
  private UUID uuid;
  @NotNull
  private String email;
  @NotNull
  private String username;
  @NotNull
  private String cellphone;
  @NotNull
  private String password;
  @NotNull
  private CitizenDto citizen;
  @NotNull
  private NotificationDto notification;
  //endregion

  //region Ctor
  public UserDto(EntityBuilder buildDto) {
    this.uuid = buildDto.uuid;
    this.email = buildDto.email;
    this.username = buildDto.username;
    this.cellphone = buildDto.cellphone;
    this.password = buildDto.password;
    this.citizen = buildDto.citizen;
    this.notification = buildDto.notification;
  }

  public UserDto() {
  }
  //endregion

  //region GetterAndSetters
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCellphone() {
    return cellphone;
  }

  public void setCellphone(String cellphone) {
    this.cellphone = cellphone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(@NotNull String password) {
    this.password = password;
  }

  public CitizenDto getCitizen() {
    return citizen;
  }

  public void setCitizen(@NotNull CitizenDto citizen) {
    this.citizen = citizen;
  }

  public @NotNull NotificationDto getNotification() {
    return notification;
  }

  public void setNotification(@NotNull NotificationDto notification) {
    this.notification = notification;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private UUID uuid;
    private String email;
    private String username;
    private String cellphone;
    private String password;
    private CitizenDto citizen;
    private NotificationDto notification;
    //endregion

    //region CtorBuilder
    public EntityBuilder(String email, String username, String cellphone, String password) {
      this.email = email;
      this.username = username;
      this.cellphone = cellphone;
      this.password = password;
    }

    public EntityBuilder() {
    }
    //endregion

    //region Builder Getter and Setter
    public EntityBuilder uuid(UUID uuid) {
      this.uuid = uuid;
      return this;
    }

    public EntityBuilder email(String email) {
      this.email = email;
      return this;
    }

    public EntityBuilder username(String username) {
      this.username = username;
      return this;
    }

    public EntityBuilder cellphone(String cellphone) {
      this.cellphone = cellphone;
      return this;
    }

    public EntityBuilder password(String password) {
      this.password = password;
      return this;
    }

    public EntityBuilder citizen(CitizenDto citizen) {
      this.citizen = citizen;
      return this;
    }

    public EntityBuilder notification(NotificationDto notification) {
      this.notification = notification;
      return this;
    }

    //endregion
    public UserDto build() {
      return new UserDto(this);
    }
  }
  //endregion

}
