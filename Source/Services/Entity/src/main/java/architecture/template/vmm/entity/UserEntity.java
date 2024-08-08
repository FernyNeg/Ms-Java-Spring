package architecture.template.vmm.entity;

import architecture.template.vmm.entity.citizen.CatUserCitizenEntity;
import architecture.template.vmm.entity.notification.CatUserNotificationEntity;
import architecture.template.vmm.entity.system.Auditable;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_ENABLED;

@Entity
@Table(name = "users")
public class UserEntity extends Auditable<String> {

  //region Variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false, updatable = false, columnDefinition = "default")
  private UUID uuid;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true, updatable = false)
  private String username;

  @Column(nullable = false, unique = true)
  private String cellphone;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Boolean validate;
  //endregion

  //region Relationships
  @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
  private Set<CatUserCitizenEntity> citizen = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
  private Set<CatUserNotificationEntity> notifications = new HashSet<>();
  //endregion

  //region Constructors
  public UserEntity(EntityBuilder builder) {
    this.id = builder.id;
    this.uuid = builder.uuid;
    this.email = builder.email;
    this.username = builder.username;
    this.cellphone = builder.cellphone;
    this.password = builder.password;
    this.validate = builder.validate;
    this.citizen = builder.citizenList;
    this.notifications = builder.notifications;
  }

  public UserEntity() {
  }
  //endregion

  //region Handling
  @PrePersist
  public void beforePersist() {
    uuid = UUID.randomUUID();
    this.setFdl(DEFAULT_FDL_ENABLED);
    validate = false;
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

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getValidate() {
    return validate;
  }

  public void setValidate(Boolean validate) {
    this.validate = validate;
  }
  //endregion

  //region Relationship Getter and setter
  public Set<CatUserCitizenEntity> getCitizen() {
    return citizen;
  }

  public void setCitizen(Set<CatUserCitizenEntity> citizen) {
    this.citizen = citizen;
  }

  public Set<CatUserNotificationEntity> getNotifications() {
    return notifications;
  }

  public void setNotifications(Set<CatUserNotificationEntity> notifications) {
    this.notifications = notifications;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private Long id;
    private UUID uuid;
    private String email;
    private String username;
    private String cellphone;
    private String password;
    private Boolean validate;
    private Set<CatUserCitizenEntity> citizenList = new HashSet<>();
    private Set<CatUserNotificationEntity> notifications = new HashSet<>();
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

    public EntityBuilder validate(Boolean validate) {
      this.validate = validate;
      return this;
    }
    //endregion

    //region Builder relationship Getters and Setters
    public EntityBuilder citizenList(Set<CatUserCitizenEntity> citizen) {
      this.citizenList = citizen;
      return this;
    }

    public EntityBuilder notifications(Set<CatUserNotificationEntity> notifications) {
      this.notifications = notifications;
      return this;
    }
    //endregion

    public UserEntity build() {
      return new UserEntity(this);
    }
  }
  //endregion

}
