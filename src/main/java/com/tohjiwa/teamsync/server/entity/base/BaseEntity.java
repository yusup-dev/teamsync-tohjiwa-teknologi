package com.tohjiwa.teamsync.server.entity.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity {
    @NotNull
    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy = "sys";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @NotNull
    @Column(name = "last_modified_by", nullable = false)
    @LastModifiedBy
    private String lastModifiedBy = "sys";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deletedAt;
}
