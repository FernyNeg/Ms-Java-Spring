package architecture.template.vmm.entity.notification;

import architecture.template.vmm.entity.UserEntity;
import architecture.template.vmm.entity.system.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_ENABLED;

@Entity
@Table(name = "cat_user_notification")
public class CatUserNotificationEntity extends Auditable<String> {
  //region Variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private UUID uuid;
  //endregion

  //region Relationships
  @ManyToOne
  @JoinColumn(name = "id_user_fk")
  @JsonBackReference
  private UserEntity user;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_cat_user_notification_fk")
  @JsonManagedReference
  private NotificationEntity config;
  //endregion

  //region Constructors
  public CatUserNotificationEntity(EntityBuilder builder) {
    this.id = builder.id;
    this.uuid = builder.uuid;
    this.user = builder.user;
    this.config = builder.config;
  }

  public CatUserNotificationEntity() {
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
  //endregion

  //region Relationship Getter and setter
  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public NotificationEntity getConfig() {
    return config;
  }

  public void setConfig(NotificationEntity config) {
    this.config = config;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private Long id;
    private UUID uuid;
    private UserEntity user;
    private NotificationEntity config;
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
    //endregion

    //region Builder Relationship Getters and Setters
    public EntityBuilder user(UserEntity user) {
      this.user = user;
      return this;
    }

    public EntityBuilder config(NotificationEntity config) {
      this.config = config;
      return this;
    }
    //endregion

    public CatUserNotificationEntity build() {
      return new CatUserNotificationEntity(this);
    }
  }
  //endregion

}
