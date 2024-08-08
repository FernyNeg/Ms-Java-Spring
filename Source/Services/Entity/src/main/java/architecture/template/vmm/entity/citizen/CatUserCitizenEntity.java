package architecture.template.vmm.entity.citizen;

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
@Table(name = "cat_user_citizen")
public class CatUserCitizenEntity extends Auditable<String> {

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
  @JoinColumn(name = "id_cat_user_citizen_fk")
  @JsonManagedReference
  private CitizenEntity citizen;
  //endregion

  //region Constructors
  public CatUserCitizenEntity(EntityBuilder builder) {
    this.id = builder.id;
    this.uuid = builder.uuid;
    this.user = builder.user;
    this.citizen = builder.citizen;
  }

  public CatUserCitizenEntity() {
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

  public CitizenEntity getCitizen() {
    return citizen;
  }

  public void setCitizen(CitizenEntity citizen) {
    this.citizen = citizen;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private Long id;
    private UUID uuid;
    private UserEntity user;
    private CitizenEntity citizen;
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

    public EntityBuilder user(UserEntity user) {
      this.user = user;
      return this;
    }

    public EntityBuilder citizen(CitizenEntity citizen) {
      this.citizen = citizen;
      return this;
    }
    //endregion

    public CatUserCitizenEntity build() {
      return new CatUserCitizenEntity(this);
    }
  }
  //endregion

}
