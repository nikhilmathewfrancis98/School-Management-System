package com.kenschool.Model_POJOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
//        import java.util.Date;

@Data
@MappedSuperclass
@JsonIgnoreProperties(value = {"updatedAt","updatedBy"})
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate // Date will be mapped from the db server by the spring data jpa
    @Column(name = "created_at", updatable = false) // simply given the name attribute
    @JsonIgnore // Since we don't need to send this data to the UI When using the REST API
    private LocalDateTime createdAt;

    @CreatedBy //here the value is inserted by auditingImpl.java
    @Column(updatable = false)
    // When updating like fetching the values we don't really need this we only fetch fields except this so we given this
    @JsonIgnore
    private String createdBy;

    @LastModifiedDate // Date will be mapped from the db server by the spring data jpa
    @Column(insertable = false)
    // in inserting we don't need this field to be inserted ie,this will be null so give this
    private LocalDateTime updatedAt;

    @LastModifiedBy //here the value is inserted by auditingImpl.java
    @Column(insertable = false) //in inserting we don't need this field to be inserted ie,this will be null so give this
    private String updatedBy;
}