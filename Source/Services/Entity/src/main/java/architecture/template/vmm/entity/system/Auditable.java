package architecture.template.vmm.entity.system;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

  //region Variables
  @CreatedBy
  @Column(name = "created_by")
  private U createdBy;

  @CreatedDate
  @Column(name = "created_date")
  private Date createdDate;

  @LastModifiedBy
  @Column(name = "last_modified_by")
  private U lastModifiedBy;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  private Date lastModifiedDate;

  private Boolean fdl;
  //endregion

  //region Getters and Setters
  public U getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(U createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public U getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(U lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Boolean getFdl() {
    return fdl;
  }

  public void setFdl(Boolean fdl) {
    this.fdl = fdl;
  }
  //endregion
}
