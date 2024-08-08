package architecture.template.vmm.entity.catalog;

import architecture.template.vmm.entity.system.Auditable;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "catalog")
public class CatalogEntity extends Auditable<String> {

  //region Variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private UUID uuid;

  private String name;
  //endregion

  //region Relationships
  @OneToMany(mappedBy = "catalog", cascade = {CascadeType.ALL})
  private Set<CatalogValueEntity> values = new HashSet<>();
  //endregion

  //region Constructors
  public CatalogEntity(EntityBuilder builder) {
    this.id = builder.id;
    this.uuid = builder.uuid;
    this.name = builder.name;
    this.values = builder.values;
  }

  public CatalogEntity() {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  //endregion

  //region Relationship Getter and setter

  public Set<CatalogValueEntity> getValues() {
    return values;
  }

  public void setValues(Set<CatalogValueEntity> values) {
    this.values = values;
  }

  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private Long id;
    private UUID uuid;
    private String name;
    private Set<CatalogValueEntity> values;
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

    public EntityBuilder name(String name) {
      this.name = name;
      return this;
    }
    //endregion

    //region Builder Relationship Getter and Setter
    public EntityBuilder value(Set<CatalogValueEntity> value) {
      this.values = value;
      return this;
    }
    //endregion

    public CatalogEntity build() {
      return new CatalogEntity(this);
    }
  }
  //endregion

}
