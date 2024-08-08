package architecture.template.vmm.entity;

import architecture.template.vmm.entity.system.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_ENABLED;

@Entity
@Table(name = "department")
public class DepartmentEntity extends Auditable<String> {

  //region Variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private UUID uuid;

  private String name;
  private String description;
  private String code;
  @Column(name = "short_name")
  private String shortname;
  //endregion

  //region Relationships
  //endregion

  //region Constructors
  public DepartmentEntity(EntityBuilder b) {
    this.id = b.id;
    this.uuid = b.uuid;
    this.name = b.name;
    this.description = b.description;
    this.code = b.code;
    this.shortname = b.shortname;
  }

  public DepartmentEntity() {
  }
  //endregion

  //region Handling
  @PrePersist
  public void beforePersist() {
    uuid = UUID.randomUUID();
    this.setFdl(DEFAULT_FDL_ENABLED);
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getShortname() {
    return shortname;
  }

  public void setShortname(String shortname) {
    this.shortname = shortname;
  }
  //endregion

  //region Relationship Getter and setter
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    private String code;
    private String shortname;
    //endregion

    //region Builder Getter and Setter
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

    public EntityBuilder description(String description) {
      this.description = description;
      return this;
    }

    public EntityBuilder code(String code) {
      this.code = code;
      return this;
    }

    public EntityBuilder shortname(String shortname) {
      this.shortname = shortname;
      return this;
    }
    //endregion

    public DepartmentEntity build() {
      return new DepartmentEntity(this);
    }
  }
  //endregion

}
