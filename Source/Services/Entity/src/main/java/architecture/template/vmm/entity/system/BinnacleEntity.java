package architecture.template.vmm.entity.system;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "binnacle")
public class BinnacleEntity {

  //region Variables
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private UUID uuid;

  @Column(name = "ip_address")
  private String ipaddress;
  private String rute;
  private String method;
  //endregion

  //region Constructors
  public BinnacleEntity(EntityBuilder b) {
    this.ipaddress = b.ipaddress;
    this.rute = b.rute;
    this.method = b.method;
  }

  public BinnacleEntity() {
  }
  //endregion

  //region Handling
  @PrePersist
  public void beforePersist() {
    uuid = UUID.randomUUID();
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

  public String getIpaddress() {
    return ipaddress;
  }

  public void setIpaddress(String ipaddress) {
    this.ipaddress = ipaddress;
  }

  public String getRute() {
    return rute;
  }

  public void setRute(String rute) {
    this.rute = rute;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }
  //endregion

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private String ipaddress;
    private String rute;
    private String method;
    //endregion

    //region Builder Getter and Setter
    public EntityBuilder ipaddress(String ipaddress) {
      this.ipaddress = ipaddress;
      return this;
    }

    public EntityBuilder rute(String rute) {
      this.rute = rute;
      return this;
    }

    public EntityBuilder method(String method) {
      this.method = method;
      return this;
    }
    //endregion

    public BinnacleEntity build() {
      return new BinnacleEntity(this);
    }
  }
  //endregion

}
