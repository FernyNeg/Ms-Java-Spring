package architecture.template.vmm.entity.catalog;

import architecture.template.vmm.entity.system.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "catalog_value")
public class CatalogValueEntity extends Auditable<String> {

  //region Variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private UUID uuid;

  private String value;
  private String description;
  //endregion

  //region Relationships
  @ManyToOne
  @JoinColumn(name = "id_catalog_fk")
  @JsonBackReference
  private CatalogEntity catalog;
  //endregion

  //region Constructors

  public CatalogValueEntity(EntityBuilder builder) {
    this.id = builder.id;
    this.uuid = builder.uuid;
    this.value = builder.value;
    this.description = builder.description;
    this.catalog = builder.catalog;
  }

  public CatalogValueEntity() {
  }

  //endregion

  //region Handling
  @PrePersist
  public void beforePersist() {
    uuid = UUID.randomUUID();
    this.setFdl(true);
  }

  @PreUpdate
  public void beforeUpdate() {
    this.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now())); // Set the timestamp before update
  }
  //endregion

  //region Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  //endregion

  //region Relationship Getter and setter
  public CatalogEntity getCatalog() {
    return catalog;
  }

  public void setCatalog(CatalogEntity catalog) {
    this.catalog = catalog;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private Long id;
    private UUID uuid;
    private String value;
    private String description;
    private CatalogEntity catalog;
    //endregion

    //region Builder Getter and Setter
    public EntityBuilder() {
    }

    public EntityBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public EntityBuilder uuid(UUID uuid) {
      this.uuid = uuid;
      return this;
    }

    public EntityBuilder value(String value) {
      this.value = value;
      return this;
    }

    public EntityBuilder description(String description) {
      this.description = description;
      return this;
    }
    //endregion

    //region Builder Relationship Getter and Setter
    public EntityBuilder catalog(CatalogEntity catalog) {
      this.catalog = catalog;
      return this;
    }
    //endregion

    public CatalogValueEntity build() {
      return new CatalogValueEntity(this);
    }
  }
  //endregion

}
