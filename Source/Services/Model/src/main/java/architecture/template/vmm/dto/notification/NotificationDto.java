package architecture.template.vmm.dto.notification;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class NotificationDto {

  //region Variables
  private UUID uuid;
  @NotNull
  private Boolean phone;
  @NotNull
  private Boolean email;
  @NotNull
  private Boolean push;
  @NotNull
  private Boolean whatsapp;
  //endregion

  //region Ctor
  public NotificationDto(EntityBuilder builder) {
    this.uuid = builder.uuid;
    this.phone = builder.phone;
    this.email = builder.email;
    this.push = builder.push;
    this.whatsapp = builder.whatsapp;

  }

  public NotificationDto() {
  }
  //endregion

  //region Getters and Setters
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public @NotNull Boolean getPhone() {
    return phone;
  }

  public void setPhone(@NotNull Boolean phone) {
    this.phone = phone;
  }

  public @NotNull Boolean getEmail() {
    return email;
  }

  public void setEmail(@NotNull Boolean email) {
    this.email = email;
  }

  public @NotNull Boolean getPush() {
    return push;
  }

  public void setPush(@NotNull Boolean push) {
    this.push = push;
  }

  public @NotNull Boolean getWhatsapp() {
    return whatsapp;
  }

  public void setWhatsapp(@NotNull Boolean whatsapp) {
    this.whatsapp = whatsapp;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private UUID uuid;
    private Boolean phone;
    private Boolean email;
    private Boolean push;
    private Boolean whatsapp;
    //endregion

    //region Builder Getter and Setter
    public EntityBuilder setUuid(UUID uuid) {
      this.uuid = uuid;
      return this;
    }

    public EntityBuilder setPhone(Boolean phone) {
      this.phone = phone;
      return this;
    }

    public EntityBuilder setEmail(Boolean email) {
      this.email = email;
      return this;
    }

    public EntityBuilder setPush(Boolean push) {
      this.push = push;
      return this;
    }

    public EntityBuilder setWhatsapp(Boolean whatsapp) {
      this.whatsapp = whatsapp;
      return this;
    }
    //endregion

    public NotificationDto build() {
      return new NotificationDto(this);
    }
  }
  //endregion

}
