package architecture.template.vmm.entity.notification;


import architecture.template.vmm.entity.system.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_ENABLED;

@Entity
@Table(name = "notification_config")
public class NotificationEntity extends Auditable<String> {
  //region Variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private UUID uuid;
  @Column(nullable = false)
  private Boolean phone;
  @Column(nullable = false)
  private Boolean email;
  @Column(nullable = false)
  private Boolean push;
  @Column(nullable = false)
  private Boolean whatsapp;
  //endregion

  //region Relationships
  @OneToOne(mappedBy = "config")
  @JsonBackReference
  private CatUserNotificationEntity catalog;
  //endregion

  //region Constructors
  public NotificationEntity(EntityBuilder b) {
    this.id = b.id;
    this.uuid = b.uuid;
    this.phone = b.phone;
    this.email = b.email;
    this.push = b.push;
    this.whatsapp = b.whatsapp;
    this.catalog = b.catalog;
  }

  public NotificationEntity() {
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

  public Boolean getPhone() {
    return phone;
  }

  public void setPhone(Boolean phone) {
    this.phone = phone;
  }

  public Boolean getEmail() {
    return email;
  }

  public void setEmail(Boolean email) {
    this.email = email;
  }

  public Boolean getPush() {
    return push;
  }

  public void setPush(Boolean push) {
    this.push = push;
  }

  public Boolean getWhatsapp() {
    return whatsapp;
  }

  public void setWhatsapp(Boolean whatsapp) {
    this.whatsapp = whatsapp;
  }
  //endregion

  //region Relationship Getter and setter
  public CatUserNotificationEntity getCatalog() {
    return catalog;
  }

  public void setCatalog(CatUserNotificationEntity catalog) {
    this.catalog = catalog;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private Long id;
    private UUID uuid;
    private Boolean phone;
    private Boolean email;
    private Boolean push;
    private Boolean whatsapp;
    private CatUserNotificationEntity catalog;
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

    public EntityBuilder phone(Boolean phone) {
      this.phone = phone;
      return this;
    }

    public EntityBuilder email(Boolean email) {
      this.email = email;
      return this;
    }

    public EntityBuilder push(Boolean push) {
      this.push = push;
      return this;
    }

    public EntityBuilder whatsapp(Boolean whatsapp) {
      this.whatsapp = whatsapp;
      return this;
    }
    //endregion

    //region Builder Relationship Getters And Setters
    public EntityBuilder catalog(CatUserNotificationEntity catalog) {
      this.catalog = catalog;
      return this;
    }
    //endregion

    public NotificationEntity build() {
      return new NotificationEntity(this);
    }
  }
  //endregion

}
