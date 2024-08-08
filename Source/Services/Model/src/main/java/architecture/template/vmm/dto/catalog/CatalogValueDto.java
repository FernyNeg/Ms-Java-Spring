package architecture.template.vmm.dto.catalog;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CatalogValueDto {

  //region Variables
  private UUID uuid;
  @NotNull
  private String value;
  private String description;
  //endregion

  //region Ctor

  public CatalogValueDto(EntityBuilder builder) {
    this.uuid = builder.uuid;
    this.value = builder.value;
    this.description = builder.description;
  }

  public CatalogValueDto() {
  }

  //endregion

  //region Getters and Setters
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public @NotNull String getValue() {
    return value;
  }

  public void setValue(@NotNull String value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private UUID uuid;
    private String value;
    private String description;
    //endregion

    //region Builder Getter and Setter
    public EntityBuilder setUuid(UUID uuid) {
      this.uuid = uuid;
      return this;
    }
    public EntityBuilder setValue(String value) {
      this.value = value;
      return this;
    }
    public EntityBuilder setDescription(String description) {
      this.description = description;
      return this;
    }
    //endregion

    public CatalogValueDto build() {
      return new CatalogValueDto(this);
    }
  }
  //endregion

}
