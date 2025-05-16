package org.jkh.scheduleapi.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDate {
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private Date createAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date modifiedAt;
}
