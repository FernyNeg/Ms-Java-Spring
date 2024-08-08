package architecture.template.vmm.entity.citizen;

import architecture.template.vmm.entity.system.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static architecture.template.vmm.constants.SystemConstants.DEFAULT_FDL_ENABLED;

@Entity
@Table(name = "citizen")
public class CitizenEntity extends Auditable<String> {

  //region Variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private UUID uuid;

  @Column
  private String names;
  @Column
  private String lastname1;
  private String lastname2;
  @Column(name = "birth_date", nullable = false)
  private Date birthdate;
  private String curp;
  private String rfc;
  //endregion

  //region Relationships
  @OneToOne(mappedBy = "citizen")
  @JsonBackReference
  private CatUserCitizenEntity catuser;
  //endregion

  //region Constructors
  public CitizenEntity(EntityBuilder b) {
    this.id = b.id;
    this.uuid = b.uuid;
    this.names = b.names;
    this.lastname1 = b.lastname1;
    this.lastname2 = b.lastname2;
    this.birthdate = b.birthdate;
    this.curp = b.curp;
    this.rfc = b.rfc;
  }

  public CitizenEntity() {
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

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    this.names = names;
  }

  public String getLastname1() {
    return lastname1;
  }

  public void setLastname1(String lastname1) {
    this.lastname1 = lastname1;
  }

  public String getLastname2() {
    return lastname2;
  }

  public void setLastname2(String lastname2) {
    this.lastname2 = lastname2;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public String getCurp() {
    return curp;
  }

  public void setCurp(String curp) {
    this.curp = curp;
  }

  public String getRfc() {
    return rfc;
  }

  public void setRfc(String rfc) {
    this.rfc = rfc;
  }
  //endregion

  //region Relationship Getter and setter
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private Long id;
    private UUID uuid;
    private String names;
    private String lastname1;
    private String lastname2;
    private Date birthdate;
    private String curp;
    private String rfc;
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

    public EntityBuilder names(String names) {
      this.names = names;
      return this;
    }

    public EntityBuilder lastname1(String lastname1) {
      this.lastname1 = lastname1;
      return this;
    }

    public EntityBuilder lastname2(String lastname2) {
      this.lastname2 = lastname2;
      return this;
    }

    public EntityBuilder birthdate(Date birthdate) {
      this.birthdate = birthdate;
      return this;
    }

    public EntityBuilder curp(String curp) {
      this.curp = curp;
      return this;
    }

    public EntityBuilder rfc(String rfc) {
      this.rfc = rfc;
      return this;
    }
    //endregion

    public CitizenEntity build() {
      return new CitizenEntity(this);
    }
  }
  //endregion

}
