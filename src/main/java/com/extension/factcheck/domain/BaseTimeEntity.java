package com.extension.factcheck.domain;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity implements Serializable {

  private static final long serialVersionUID = -1039316944866564968L;

  @CreatedDate
  protected OffsetDateTime dateCreated;

  @LastModifiedDate
  protected OffsetDateTime dateUpdated;

  public OffsetDateTime getDateCreated() {
    return dateCreated;
  }

  public OffsetDateTime getDateUpdated() {
    return dateUpdated;
  }
}