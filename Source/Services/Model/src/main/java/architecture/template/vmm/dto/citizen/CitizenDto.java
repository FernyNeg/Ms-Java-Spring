package architecture.template.vmm.dto.citizen;

import java.sql.Date;
import java.util.UUID;

public class CitizenDto {

  //region Variables
  private UUID uuid;
  private String names;
  private String lastname1;
  private String lastname2;
  private Date birthdate;
  private String curp;
  private String rfc;
  //endregion

  //region Ctor
  public CitizenDto(EntityBuilder builder) {
    this.uuid = builder.uuid;
    this.names = builder.names;
    this.lastname1 = builder.lastname1;
    this.lastname2 = builder.lastname2;
    this.birthdate = builder.birthdate;
    this.curp = builder.curp;
    this.rfc = builder.rfc;
  }

  public CitizenDto() {
  }
  //endregion

  //region Getters and Setters
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

  //region Builder
  public static class EntityBuilder {
    //region VariablesBuilder
    private UUID uuid;
    private String names;
    private String lastname1;
    private String lastname2;
    private Date birthdate;
    private String curp;
    private String rfc;
    //endregion

    //region Builder Getter and Setter
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

    public CitizenDto build() {
      return new CitizenDto(this);
    }
  }
  //endregion

}
