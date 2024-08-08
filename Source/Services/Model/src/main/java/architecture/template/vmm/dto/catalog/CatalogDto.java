package architecture.template.vmm.dto.catalog;

import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CatalogDto {

  //region Variables
  private UUID uuid;
  @NotNull
  private String name;
  //endregion

  //region Relationship
  @NotNull
  private Set<CatalogValueDto> values = new HashSet<>();
  //endregion

  //region Ctor
  public CatalogDto(EntityBuilder builder) {
    this.uuid = builder.uuid;
    this.name = builder.name;
    this.values = builder.values;
  }

  public CatalogDto() {
  }
  //endregion

  //region Getters and Setters
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  //endregion

  //region Relationship Getter and Setter
  public Set<CatalogValueDto> getValues() {
    return values;
  }

  public void setValues(Set<CatalogValueDto> values) {
    this.values = values;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private UUID uuid;
    private String name;
    private Set<CatalogValueDto> values = new HashSet<>();
    //endregion

    //region Builder Getter and Setter
    public EntityBuilder uuid(UUID uuid) {
      this.uuid = uuid;
      return this;
    }

    public EntityBuilder name(String name) {
      this.name = name;
      return this;
    }
    //endregion

    //region Builder Relationship Getter and Setter
    public EntityBuilder values(Set<CatalogValueDto> values) {
      this.values = values;
      return this;
    }
    //endregion

    public CatalogDto build() {
      return new CatalogDto(this);
    }
  }
  //endregion

}
